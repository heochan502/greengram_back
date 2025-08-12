package com.green.greengram.application.feed.model;


import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter

public class FeedPostReq {
//    private Long writeUserId;

    @Size(max = 1_000, message = "내용은 1,000자 이하여야 합니다.")
    private String contents;


    @Size(max = 30, message = "location은 30자 이하여야 합니다.")
    private String location;
}
