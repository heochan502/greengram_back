package com.green.greengram.config.constants;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "constants.comment")
@RequiredArgsConstructor
public class ConstComment {
    // yml 파일에서 설정 하고 관리함
    public final int startIndex; // 댓글 가져오는거 시작점
    public final int needForViewCount; // 몇개 가져올지 가져오는 거
}
