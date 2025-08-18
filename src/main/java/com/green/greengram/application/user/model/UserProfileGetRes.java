package com.green.greengram.application.user.model;


import com.green.greengram.entity.FeedComment;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@ToString
public class UserProfileGetRes {
    private Long userId;
    private String pic;
    private String createdAt;
    private String uid;
    private String nickName;
    private int feedCount;// 프로파일 유저의 등록한 피드 수

    private int allFeedLikeCount; // 나의 모든 피드이 좋아요 수


    private int followerCount; // 나를 팔로우 하는 사람들의 수
    private int followingCount; // 내가 팔로우 하는 사람들의 수

    private int followState; //팔로우 상태
}
