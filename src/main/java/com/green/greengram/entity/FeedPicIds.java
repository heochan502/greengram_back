package com.green.greengram.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
//@Setter
@Embeddable
@EqualsAndHashCode
// 빌더 쓸려면 아래 다적어야함
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedPicIds  implements Serializable {


    private Long feedId;
    @Column(nullable = false, length = 50)
    private String pic;
}
