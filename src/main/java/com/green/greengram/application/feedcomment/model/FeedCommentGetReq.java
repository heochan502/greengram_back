package com.green.greengram.application.feedcomment.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class FeedCommentGetReq {
    private Long feedId;
    private int startIdx;
    private int size;

    public FeedCommentGetReq(Long feedId, int startIdx, int size) {
        this.feedId = feedId;
        this.startIdx = startIdx;
        this.size = size;
    }
}
