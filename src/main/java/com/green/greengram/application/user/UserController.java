package com.green.greengram.application.user;


import com.green.greengram.application.user.model.UserSignUpReq;
import com.green.greengram.config.model.ResultResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResultResponse<?> signUp(@Valid @RequestPart UserSignUpReq req,
                                    @RequestPart(required = false) MultipartFile pic) { // 필수는 아니라서 required false

        log.info("req : {}", req);
        log.info("pic : {}", pic!= null? pic.getOriginalFilename() : pic);
        return new ResultResponse<Integer>("",1);
    }

}