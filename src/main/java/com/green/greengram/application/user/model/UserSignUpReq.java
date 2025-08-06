package com.green.greengram.application.user.model;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
// 이게validation
public class UserSignUpReq {
    @Size(max = 50,min= 4, message = "아이디는 4~50자 까지 작성 할 수 있습니다.")// uid 최대 최소 글자수 설정
    @Pattern(regexp = "[A-Za-z0-9]_{4,50}", message = "아이디는 영어,숫자, 언더바로만 작성가능하고 4자이상 50이하 까지만 가능합니다.")
    private String uid;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{10,}$", message = "비밀번호는 대소문자, 숫자, 특수문자를 포함하여 10자 이상이어야 합니다." )
    private String upw;
    @Pattern(regexp ="^[가-힣]{2,10}$", message="닏네임은 한글로 2~10자 까지 가능합니다." )
    private String nickName;
}
