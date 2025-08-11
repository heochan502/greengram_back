package com.green.greengram.application.feed;

import com.green.greengram.application.feed.model.FeedGetDto;
import com.green.greengram.application.feed.model.FeedPostRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface FeedMapper {

    public List<FeedPostRes> findAllLimitedId(FeedGetDto feedGetDto);
}
