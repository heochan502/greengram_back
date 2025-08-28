package com.green.greengram.application.feed;

import com.green.greengram.application.feed.model.FeedGetDto;
import com.green.greengram.application.feed.model.FeedGetRes;
import com.green.greengram.application.feed.model.FeedKeywordGetReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper

public interface FeedMapper {

    List<FeedGetRes> findAllLimitedId(FeedGetDto feedGetDto);
    List<String> findAllPicByFeedId(Long FeedId);
    Set<String> findAllByKeyword(FeedKeywordGetReq req);
}
