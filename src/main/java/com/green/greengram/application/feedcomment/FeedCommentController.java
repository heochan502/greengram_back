package com.green.greengram.application.feedcomment;


import com.green.greengram.application.feed.FeedService;
import com.green.greengram.application.feed.model.FeedPostReq;
import com.green.greengram.application.feedcomment.model.FeedCommentPostReq;
import com.green.greengram.config.model.ResultResponse;
import com.green.greengram.config.model.UserPrincipal;
import com.green.greengram.entity.FeedComment;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/feed/comment")
public class FeedCommentController {
    private final FeedCommentService feedCommentService;

    @PostMapping
    public ResultResponse<?> postFeedComment (@AuthenticationPrincipal UserPrincipal userPrincipal,
                                              @Valid @RequestBody FeedCommentPostReq req){
        log.info("user : {}", userPrincipal.getSignedUserId());
        log.info("req : {}",req);
        Long feedCommentId = feedCommentService.postFeedComment(userPrincipal.getSignedUserId(), req);
        log.info("댓글 등록 번호  : {}",feedCommentId);
        return new ResultResponse<>(String.format("댓글 입력 완료. "), feedCommentId);
    };


}
