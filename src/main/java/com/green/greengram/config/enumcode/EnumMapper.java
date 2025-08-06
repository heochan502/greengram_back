package com.green.greengram.config.enumcode;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EnumMapper {
    private Map<String, List<EnumMapperType>> factory = new LinkedHashMap<>();
    //순차적 방번호가 있어서 순차적으로 저장한다 sequential 으로 저장
    // String 이라는 key값고 value 라는 값을 저장 한다 factory에

    //e는 EnumMapperValue를 상속받는 어떤 타입이든 전달 될 수 있다.// 제네릭 generic
    public void put(String key, Class<? extends EnumMapperType> e)
                // EnumMapperType 이걸 상속받았다면 누구든 key으로 데이터를 넘겨서 보낼 수 있다
    {
        factory.put(key, toEnumValues(e));
    }
    private List<EnumMapperType> toEnumValues(Class<? extends EnumMapperType> e)
    {
        e.getEnumConstants(); // 특정 enum 타입이 갖고 있는 모든 값을 출력시키는 기능은 Class의 getEnumConstants()매소드를 사용
        return  null;
    }

}
