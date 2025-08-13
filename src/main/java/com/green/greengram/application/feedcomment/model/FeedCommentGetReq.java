package com.green.greengram.application.feedcomment.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.bind.annotation.BindParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.beans.ConstructorProperties;

@Getter

@ToString
public class FeedCommentGetReq {

    @Positive
    @NotNull(message = "feedId 값은 필수입니다.")
    private Long feedId;

    @PositiveOrZero
    @NotNull(message = "startIdx 값은 필수입니다. ")
    private Integer startIdx;

    @Min(3) @Max(50)
    @NotNull(message = "size값은 1 이상이어야 합니다")
    private Integer size;

    private Integer sizePlusOne;

// 밑의 바인드 파람이랑 똑같음 대신 모든 컬럼 다 적어야함
//    @ConstructorProperties({"feed_id", "start_idx", "size"})
    public FeedCommentGetReq(@BindParam("feed_id") Long feedId , @BindParam("start_idx")Integer startIdx, Integer size) {
        this.feedId = feedId;
        this.startIdx = startIdx;
        this.size = size;
        this.sizePlusOne = size + 1;

    }

//    public Integer getSizePlusOne()
//    {
//        return size +1;
//    }
}
