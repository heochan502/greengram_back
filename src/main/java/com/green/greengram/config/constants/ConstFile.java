package com.green.greengram.config.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "constants.file")
@RequiredArgsConstructor
@ToString
public class ConstFile {
    //yaml 파일 확인
    private final String uploadDirectory;
    private final String feedPic;
    private final String profilePic;
}