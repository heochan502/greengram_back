package com.green.greengram.application.feed;


import com.green.greengram.application.feed.model.FeedGetDto;
import com.green.greengram.application.feed.model.FeedGetRes;
import com.green.greengram.application.feed.model.FeedPostReq;
import com.green.greengram.application.feed.model.FeedPostRes;
import com.green.greengram.application.feedcomment.FeedCommentMapper;
import com.green.greengram.application.feedcomment.FeedCommentRepository;
import com.green.greengram.application.feedcomment.FeedCommentService;
import com.green.greengram.application.feedcomment.model.FeedCommentGetReq;
import com.green.greengram.application.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.application.feedcomment.model.FeedCommentItem;
import com.green.greengram.application.feedlike.FeedLikeRepository;
import com.green.greengram.application.user.UserRepository;
import com.green.greengram.config.constants.ConstComment;
import com.green.greengram.config.model.ResultResponse;
import com.green.greengram.config.model.UserPrincipal;
import com.green.greengram.config.util.ImgUploadManager;
import com.green.greengram.entity.Feed;
import com.green.greengram.entity.FeedLikeIds;
import com.green.greengram.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepository feedRepository;
    private final ImgUploadManager imgUploadManager;
    private final FeedMapper feedMapper;
    private final FeedCommentMapper feedCommentMapper;
    private final ConstComment constComment;
    private final FeedCommentService feedCommentService;
    private final FeedLikeRepository feedLikeRepository;
    private final FeedCommentRepository feedCommentRepository;

    @Transactional
    public FeedPostRes postFeed(Long signedUserId , FeedPostReq req, List<MultipartFile> pics)
    {
        User writerUser = new User();
        writerUser.setUserId(signedUserId);

        Feed feed = Feed.builder()
                .writerUserId(writerUser)
                .location(req.getLocation())
                .contents(req.getContents())
                .build();

        feedRepository.save(feed);
       // feed 객체는 영속성을 갖는다
        List<String> fileNames = imgUploadManager.saveFeedPics(feed.getFeedId(),pics);
            feed.addFeedPics(fileNames);
        return new FeedPostRes(feed.getFeedId(), fileNames);
    }

    public List <FeedGetRes>  getFeedList(FeedGetDto feedGetDto)
    {
        List<FeedGetRes> list = feedMapper.findAllLimitedId(feedGetDto);
        // 각 피드에서 사진 가져오기 // 댓글 가져오기 (4개만)
//      final int COMMENT_STARTIDX = 0;
//      final int NEED_FOR_VIEW_SIZE = 3;// 피드 리스트를 뿌릴 떄 실제로 피드당 보여지는 댓글 수
//      final int MORE_COMMENT_COUNT = 4;
        for (FeedGetRes feedGetRes : list) {
            feedGetRes.setPics(feedMapper.findAllPicByFeedId(feedGetRes.getFeedId()));

            //startIdx : 0, size : 4

            FeedCommentGetReq req = new FeedCommentGetReq(  feedGetRes.getFeedId(), constComment.startIndex , constComment.needForViewCount );
            List<FeedCommentItem> commentList = feedCommentMapper.findAllByFeedIdLimitedTo(req);
            boolean moreComment = commentList.size() > constComment.needForViewCount; // row수가 4였을 때만 true가 담기고 row수가 0~3인 경우는 false가 담긴다.
            FeedCommentGetRes feedCommentGetRes = new FeedCommentGetRes(  moreComment ,commentList);
            feedGetRes.setComments( feedCommentGetRes );
            if(moreComment) // 마지막 댓글 삭제
            {
                commentList.remove(commentList.size() - 1);
            }
        }
//        return  feedMapper.findAllLimitedId(feedGetDto) ;
        return list;
    }

    @Transactional
    public void deleteFeed(Long signedUserId, Long feedId )
    {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "feed_id가 존재 하지 않습니다."));
        if (feed.getWriterUserId().getUserId() != signedUserId){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "피드 삭제 권한이 없습니다.");

        }
        // 해당 피드 좋아요 삭제
//        FeedLikeIds feedLikeIds = FeedLikeIds.builder()
//                .feedId(feedId)
//                .userId(signedUserId)
//                .build();

        feedLikeRepository.deleteByFeedLikeIdsFeedId(feedId);

        //해당 피드 댓글 삭제

//        feedCommentService.deleteFeedCommentByFeedId(feedId);

        feedCommentRepository.deleteByFeedIdFeedId(feedId);

        // 피드 삭제
        feedRepository.delete(feed);

        //해당 피드 사진 폴더 삭제
        imgUploadManager.removeFeedDirectory(feedId);


    }


}
