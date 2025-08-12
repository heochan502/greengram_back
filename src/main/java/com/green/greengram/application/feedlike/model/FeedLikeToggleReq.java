package com.green.greengram.application.feedlike.model;


import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.ToString;

@Getter
//@Builder
@ToString
public class FeedLikeToggleReq {
    @Positive // 1이상의 수만 들어갈 수 있다.
    private Long feedId;

}
