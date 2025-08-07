package com.green.greengram.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass // 부모 역할 컬럼 // 이 class를 상속 하면 createdAt 컬럼을 가지게 된다. 상속받을 수 있도록 하는 애노테이션
@EntityListeners(AuditingEntityListener.class) // 이벤트 연결 insert 가 될 때 현재일시값 넣을 수 있도록 감시한다.
// 얘를 상속받으면 하위 상속받는 class 에서 테이블 만들때 아래의 컬럼이 추가되어서 다만들어지게 된다
// 모든 테이블에 createdAt이 있으니까 얘를 상속받으면 해당 컬럼을 다 만들 수가 있으니 아래는 현재일시값을 넣는 작업
public class CreatedAt {
    @CreatedDate // 현재값 생성해서 넣는다 // 인서트 일때만
    @Column(nullable = false) //NOT NULL 줄때 사용  // 상속 받으면 아래의 컬럼을 만들게 하는 것
    private LocalDateTime createdAt; // 공통적인 컬럼을 넣기위해서 이짓을 하는중임

}
