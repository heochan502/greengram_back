package com.green.greengram.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
//@Setter
@Entity
@EqualsAndHashCode
// 빌더 쓸려면 아래 다 필요함
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class FeedPic  extends  CreatedAt {

    @EmbeddedId
    private FeedPicIds feedPicIds;


    // 관계설정
    @JoinColumn(name="feed_id")
    @ManyToOne()
    @MapsId("feedId")
    private Feed feed;
}
