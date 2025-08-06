package com.green.greengram.config.enumcode;



import io.micrometer.common.util.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.EnumSet;

//기본생성자인데 private // 객체 생성안할라고
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class EnumConvertUtils {


    //Code to String
    //String to Code


    //String(Code 값) to Enum
    //리턴타입이 Enum이여야 하고 EnumMapperType을 상속받은 enum이여야 한다.
    //enumClass는 Enum 타입이여야 한다.
    //Enum에 있는 값 중 매개변수 code와 같은 값을 찾아 리턴하기 위함. 근데 같은게 없으면 return null
    public static <E extends Enum<E> & EnumMapperType > E ofCode(Class<E> enumClass, String code) {
        if(StringUtils.isBlank(code))
        {
            return null;
        } //code 매개변수가 null이거나 빈칸인 경우 return null 처리
        return EnumSet.allOf(enumClass).stream()// enum을 Stream화 하기 위함
                .filter(item -> item.getCode().equals(code))
                .findFirst().orElse(null);
    }

    //Enum to String(Code 값)
    public static <E extends Enum<E> & EnumMapperType > String toCode(E enumItem){
        if(enumItem == null)
        {
            return null;
        }
        return enumItem.getCode();
    }

}
