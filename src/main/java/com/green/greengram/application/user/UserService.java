package com.green.greengram.application.user;


import com.green.greengram.application.user.model.UserSignUpReq;
import com.green.greengram.config.util.ImgUploadManager;
import com.green.greengram.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImgUploadManager imgUploadManager;

    @Transactional // 트랜젝션 기능
    public void signup(UserSignUpReq req, MultipartFile pic)
    {
        String hashedPassword = passwordEncoder.encode(req.getUpw());


        User user = new User();
        user.setUserName(req.getNickName());
        user.setUid(req.getUid());
        user.setUpw(hashedPassword);
        user.addUserRole(req.getRoles());

        userRepository.save(user);

        if(pic != null) {
            String savedFileName = imgUploadManager.saveProfilePic(user.getUserId(), pic);
            user.setPic(savedFileName);
//            throw new RuntimeException(); // 강제로 예외 발생시켜서 트랜젝션 테스트용
        }
    }

}