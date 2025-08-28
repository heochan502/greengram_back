package com.green.greengram.application.feed.model;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedGetDto {
    private Long signedUserId;
    private int startIdx;
    private int size;
    private Long profileUserId;
    private String keyword;
}
