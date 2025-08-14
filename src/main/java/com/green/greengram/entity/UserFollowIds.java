package com.green.greengram.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;


@Getter
@Builder
@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
//@AllArgsConstructor
//@NoArgsConstructor
// 여기가 복합기 설정 하는 곳
public class UserFollowIds implements Serializable {

    private Long fromUserId;

    private Long toUserId;

}

