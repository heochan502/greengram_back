package com.green.greengram.application.feedlike;

import com.green.greengram.application.feedlike.model.FeedLikeToggleReq;
import com.green.greengram.config.model.UserPrincipal;
import com.green.greengram.entity.Feed;
import com.green.greengram.entity.FeedLike;
import com.green.greengram.entity.FeedLikeIds;
import com.green.greengram.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@RequiredArgsConstructor

public class FeedLikeService {
    private final FeedLikeRepository feedLikeRepository;

    public boolean toggle (Long signedUserId , FeedLikeToggleReq req) {

        FeedLikeIds feedLikeIds = FeedLikeIds.builder()
                .feedId(req.getFeedId())
                .userId(signedUserId)
                .build();
        //return이 optional상태이면 뒤에 리턴하고 싶은 값을 뒤에 orElse 적으면
        // 객체화된 내용이있는 객체를 리턴 할 수도 있다.
        // 후처리 나 예외를 출력할 수도 있다.
        // 아래 식과 같은 표현이라고 생각하면 된다
        //if (feedLike==null){ 에러 터트리겠다 }
        FeedLike feedLike = feedLikeRepository.findById(feedLikeIds).orElse(null);

        if(feedLike == null) { // 좋아요가 아닌 상태 >> 좋아요인 상태로 변경
            Feed feed = Feed.builder()
                    .feedId(feedLikeIds.getFeedId())
                    .build();

            User user = new User();
            user.setUserId(signedUserId);
            FeedLike feedLikeSave = FeedLike.builder()
                    .feedLikeIds(feedLikeIds)
                    .userId(user)
                    .feedId(feed)
                    .build();
            feedLike = feedLikeRepository.save(feedLikeSave);
            return true;
        }


         // 좋아요인 상태
        feedLikeRepository.delete(feedLike);
        return false;
    }
}
