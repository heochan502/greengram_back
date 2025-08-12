package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class FeedLike extends CreatedAt {

    @EmbeddedId
    private FeedLikeIds feedLikeIds;


    @ManyToOne
    @JoinColumn(name="feed_id")
    @MapsId("feedId")
    private Feed feedId;

    @ManyToOne
    @JoinColumn(name="user_id")
    @MapsId("userId")
    private User userId;

}
