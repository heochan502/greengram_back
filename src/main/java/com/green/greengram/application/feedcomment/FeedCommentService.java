package com.green.greengram.application.feedcomment;


import com.green.greengram.application.feedcomment.model.FeedCommentPostReq;
import com.green.greengram.entity.Feed;
import com.green.greengram.entity.FeedComment;
import com.green.greengram.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor

public class FeedCommentService {
    private final FeedCommentRepository feedCommentRepository;
    private final FeedCommentMapper feedCommentMapper;

    public Long postFeedComment(Long signedUserId, FeedCommentPostReq req) {

//        FeedCommentPostDto comment = FeedCommentPostDto.builder()
//                .userId( signedUserId )
//                .feedId(req.getFeedId())
//                .comment(req.getComment())
//                .build();

        User writerUser = new User();
        writerUser.setUserId(signedUserId);

        Feed feed = Feed.builder().feedId(req.getFeedId()).build();

        FeedComment feedComment =  FeedComment.builder()
                .userId(writerUser)
                .feedId(feed)
                .comment(req.getComment())
                .build();
        feedCommentRepository.save(feedComment);
        log.info("log : {}"  , feedComment.getFeedCommentId() );



        return feedComment.getFeedCommentId();
    }
}
