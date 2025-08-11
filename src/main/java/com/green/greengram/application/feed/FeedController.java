package com.green.greengram.application.feed;


import com.green.greengram.application.feed.model.FeedGetReq;
import com.green.greengram.application.feed.model.FeedPostReq;
import com.green.greengram.config.model.ResultResponse;
import com.green.greengram.config.model.UserPrincipal;
import com.green.greengram.entity.FeedPicIds;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController
{
    private final FeedService feedService;

    @PostMapping
    // RequestPart 파일 올라오면 이거 써야함
    public ResultResponse<?> postFeed(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                      @Valid @RequestPart FeedPostReq req ,
                                      @RequestPart(name = "pic") List<MultipartFile> pics)
    {
        log.info("user : {}", userPrincipal.getSignedUserId());
        log.info("req : {}",req);
        log.info("pics : {}",pics.size());
        feedService.postFeed(userPrincipal.getSignedUserId(), req, pics);
        return new ResultResponse<>("피드 등록 완료 ", null);
    }
    // 페이징 피드 (사진, 댓글(3개만))
    // 현재는 피드랑 사진만(N+1)2가지 정도만?

    @GetMapping
    public ResultResponse<?> getFeedList (@Valid @ModelAttribute FeedGetReq req)
    {
        log.info("req : {}",req);
        return null;
    }



}
