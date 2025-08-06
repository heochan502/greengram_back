package com.green.greengram.config.enumcode.model;


import com.green.greengram.config.enumcode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
// 구조면서 객체화 까지 가지고 있다
// 하나하나 이넘안에 있는 변수들은 값을 가질 수가 있다.

public enum EnumUserRole  implements EnumMapperType {

    USER_1("01","유저1")
    ,USER_2("02","유저2")
    ,ADMIN("03","관리자")
    ,MENTO("04","멘토")
    ,MANAGER("05","매니저")
    ;


    private final String code;
    private final String value;
}
