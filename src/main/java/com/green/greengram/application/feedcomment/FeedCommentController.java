package com.green.greengram.application.feedcomment;

import com.green.greengram.application.feedcomment.model.FeedCommentGetReq;
import com.green.greengram.application.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.application.feedcomment.model.FeedCommentPostReq;
import com.green.greengram.config.model.ResultResponse;
import com.green.greengram.config.model.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResultResponse<?> getFeedCommentList(@Valid @ModelAttribute FeedCommentGetReq req)
    {
        log.info("req : {}",req);
        FeedCommentGetRes res = feedCommentService.getFeedList(req);
        return new ResultResponse<>(String.format("row:%d", res.getCommentList().size()), res);
    }

}
