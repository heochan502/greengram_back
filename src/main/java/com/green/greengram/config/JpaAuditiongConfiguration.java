package com.green.greengram.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
// jpa 사용 하는데 안되서 추가 환경 설정 TDD 할 때 분리해야할것 같다 해서 분리 해두고 시작
public class JpaAuditiongConfiguration {
}
