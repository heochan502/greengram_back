package com.green.greengram.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter

@Embeddable
@EqualsAndHashCode
// 빌더 쓸려면 아래 다적어야함
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedLikeIds implements Serializable {

    private Long feedId;

    private Long userId;
}
