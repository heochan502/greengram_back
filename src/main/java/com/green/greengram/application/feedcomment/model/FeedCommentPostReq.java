package com.green.greengram.application.feedcomment.model;

import com.green.greengram.entity.FeedComment;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class FeedCommentPostReq {
    @Positive
    private long feedId;

    @NotNull (message = "댓글 내용은 필수 입니다." )
    @Size( min = 1, max=150, message = "댓글 내용은 2자 이상 150자 이하만 가능합니다. ")
    private String comment;
}
