package com.green.greengram.application.feed.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Setter
public class FeedGetRes {
    private Long feedId;
    private String contents;
    private String location;
    private String createdAt;

    private Long writerUserId;
    private String writerUid;
    private String writerNickName;
    private String writerPic;

    private int isLike; //0: 좋아요가 안된 피드 // 1 이면 내가 좋아요한

    private List<String> pics;
//    private String updatedAt;

}
