package com.green.greengram.application.user;


import com.green.greengram.application.user.model.*;
import com.green.greengram.config.jwt.JwtTokenManager;
import com.green.greengram.config.model.ResultResponse;
import com.green.greengram.config.model.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenManager jwtTokenManager;

    @PostMapping("/sign-up")
    public ResultResponse<?> signUp(@Valid @RequestPart UserSignUpReq req,
                                    @RequestPart(required = false) MultipartFile pic) { // 필수는 아니라서 required false

        log.info("req : {}", req);
        log.info("pic : {}", pic!= null? pic.getOriginalFilename() : pic);
        userService.signup(req,pic);
        return new ResultResponse<Integer>("",1);
    }

    @PostMapping("/sign-in")
    public ResultResponse<?> signIn(@Valid @RequestBody UserSignInReq req, HttpServletResponse response) {
        log.info("req : {}", req);
        UserSignDto userSignDto = userService.signIn(req);
        jwtTokenManager.issue(response, userSignDto.getJwtUser());
        return new ResultResponse<>("sign-in 성공 ",userSignDto.getUserSignInRes());
    }
    @PostMapping("/sign-out")
    public ResultResponse<?> signOut(HttpServletResponse response) {
        jwtTokenManager.signOut(response);
        return new ResultResponse<>("sign-out 성공 ",null);
    }

    @PostMapping("/reissue")
    public ResultResponse<?> signOut(HttpServletResponse response, HttpServletRequest request) {
        jwtTokenManager.reissue(request, response);
        return new ResultResponse<>("AccessToken 재발행 성공 ",null);
    }

    @GetMapping("/profile")
    public ResultResponse<?> getUser(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                    @RequestParam("profile_user_id") Long profileUserId) {

        log.info("profileUserId : {}", profileUserId);
        UserProfileGetDto dto = new UserProfileGetDto(userPrincipal.getSignedUserId(), profileUserId);
        UserProfileGetRes userProfileGetRes = userService.getProFileUser(dto);
        return new ResultResponse<>("프로파일 유저 정보", userProfileGetRes);

    }
    @PatchMapping("/profile/pic")
    public ResultResponse<?> patchProfilePic(@AuthenticationPrincipal UserPrincipal userPrincipal
            , @RequestPart MultipartFile pic) {
        String savedFileName = userService.patchProfilePic(userPrincipal.getSignedUserId(), pic);
        return new ResultResponse<>("프로파일 사진 수정 완료", savedFileName);
    }

}