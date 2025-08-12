package com.green.greengram.application.feed;


import com.green.greengram.application.feed.model.FeedGetDto;
import com.green.greengram.application.feed.model.FeedGetRes;
import com.green.greengram.application.feed.model.FeedPostReq;
import com.green.greengram.application.feed.model.FeedPostRes;
import com.green.greengram.application.user.UserRepository;
import com.green.greengram.config.util.ImgUploadManager;
import com.green.greengram.entity.Feed;
import com.green.greengram.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepository feedRepository;
    private final ImgUploadManager imgUploadManager;
    private final FeedMapper feedMapper;
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
        // 각 피드에서 사진 가져오기
        for (FeedGetRes feedGetRes : list) {
            feedGetRes.setPics(feedMapper.findAllPicByFeedId(feedGetRes.getFeedId()));
        }

//        return  feedMapper.findAllLimitedId(feedGetDto) ;
        return list;
    }

}
