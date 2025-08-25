package com.green.greengram.application.user;


import com.green.greengram.application.user.model.*;
import com.green.greengram.config.enumcode.model.EnumUserRole;
import com.green.greengram.config.model.JwtUser;
import com.green.greengram.config.util.ImgUploadManager;
import com.green.greengram.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImgUploadManager imgUploadManager;
    private final UserMapper userMapper;

    @Transactional // 트랜젝션 기능
    public void signup(UserSignUpReq req, MultipartFile pic)
    {
        String hashedPassword = passwordEncoder.encode(req.getUpw());


        User user = new User();
        user.setNickName(req.getNickName());
        user.setUid(req.getUid());
        user.setUpw(hashedPassword);
        user.addUserRole(req.getRoles());

        userRepository.save(user);
        // 레파지토리는 update 가 따로없다 값이 틀리면 자동으로 수정을 한다 ?

//        log.info("req.getNickName: {}", req.getNickName());
        if(pic != null) {
            String savedFileName = imgUploadManager.saveProfilePic(user.getUserId(), pic);
            user.setPic(savedFileName);
//            throw new RuntimeException(); // 강제로 예외 발생시켜서 트랜젝션 테스트용
        }
    }
    public UserSignDto signIn(UserSignInReq req)
    {
        User user = userRepository.getUserByUid(req.getUid());
        // raw가 입력받은 들어온 날것의 비번 / 뒤에가 db에 있는 비밀번호
        // 비밀번호 비교구문
        if( user == null || !passwordEncoder.matches(req.getUpw(), user.getUpw()))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "아아디/비밀번호를 확인해 주세요.");

        }

        //user 튜플을 가져왔는데. user_role에 저장 되어있는 데이터 까지 가져올 수 있었던건 양방향 관계설정 때문에 가능
        List<EnumUserRole> roles = user.getUserRoles().stream().map(item->item.getUserRoleIds().getRoleCode()).toList();
        log.info("roles: {}", roles);
        JwtUser jwtUser = new JwtUser(user.getUserId(),roles);

        UserSignInRes userSignInRes = UserSignInRes.builder()
                .userId(user.getUserId())
                .nickName(user.getNickName())
                .pic(user.getPic())
                .build();

        return UserSignDto.builder()
                .jwtUser(jwtUser)
                .userSignInRes(userSignInRes)
                .build();
    }

    public UserProfileGetRes getProFileUser(UserProfileGetDto dto )
    {
        return userMapper.findProfileByUserId(dto);
    }

    @Transactional // 다이렉트로 트렌젝션을 걸어야한다
    public String patchProfilePic(Long signedUserId, MultipartFile pic)
    {
        User user = userRepository.findById(signedUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 사용자입니다."));

        imgUploadManager.removeProfileDirectory(signedUserId);
        String savedFileName = imgUploadManager.saveProfilePic(signedUserId, pic);
        user.setPic(savedFileName);
        return savedFileName;
    }

    @Transactional 
    public void deleteProfilePic (Long signedUserId)
    {
        User user = userRepository.findById(signedUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 사용자입니다."));

        imgUploadManager.removeProfileDirectory(signedUserId);
        user.setPic(null);
        log.info("성공: {}", user );
//        userRepository.save(user);
    }
}