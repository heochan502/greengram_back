package com.green.greengram.application.feedcomment;


import com.green.greengram.application.feedcomment.model.FeedCommentGetReq;
import com.green.greengram.application.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.application.feedcomment.model.FeedCommentItem;
import com.green.greengram.application.feedcomment.model.FeedCommentPostReq;
import com.green.greengram.config.constants.ConstComment;
import com.green.greengram.entity.Feed;
import com.green.greengram.entity.FeedComment;
import com.green.greengram.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class FeedCommentService {
    private final FeedCommentRepository feedCommentRepository;
    private final FeedCommentMapper feedCommentMapper;
    private final ConstComment constComment;

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

    public FeedCommentGetRes getFeedList(FeedCommentGetReq req)
    {

        List<FeedCommentItem> commentList =  feedCommentMapper.findAllByFeedIdLimitedTo(req);

        boolean moreComment = commentList.size() > (req.getSize()); // row수가 4였을 때만 true가 담기고 row수가 0~3인 경우는 false가 담긴다.
       if
       ( moreComment)
       {
          commentList.remove(commentList.size()-1);
       }
        return new FeedCommentGetRes(moreComment, commentList);
    }

    public void deleteFeedComment(Long signedUserId , Long feedCommentId)
    {

        FeedComment feedComment = feedCommentRepository.findById(feedCommentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"feed_comment_id를 확인해 주세요."));

        // 본인이 작성한 댓글이 아니라면 exception 터트림 "본인이 작성한 댓글이 아닙니다. "

        if (!feedComment.getUserId().getUserId().equals(signedUserId) )
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"본인이 작성한 댓글이 아닙니다.");
            }
        // 댓글 삭제
        feedCommentRepository.deleteById(feedCommentId);



    }
}
