package com.green.greengram.application.feedcomment.model;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor

public class FeedCommentGetRes {
        private Boolean moreComment; // 댓글 더 있다 정보. 댓글 4개보다 적다 false / 4개보다 많다 true

        private List<FeedCommentItem> commentList; // 댓글 리스트 정보

}
