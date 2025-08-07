package com.green.greengram.application.user.model;

import com.green.greengram.config.model.JwtUser;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSignDto {
    private UserSignInRes userSignInRes; //  응답용
    private JwtUser jwtUser; // JWT 발행 떄 사용
}
