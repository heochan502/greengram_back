package com.green.greengram.application.feed.model;


import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter

public class FeedPostReq {
//    private Long writeUserId;

    @Size(max = 1_000)
    private String contents;


    @Size(max = 30)
    private String location;
}
