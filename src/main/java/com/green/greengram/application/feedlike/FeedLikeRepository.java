package com.green.greengram.application.feedlike;

import com.green.greengram.entity.FeedLike;
import com.green.greengram.entity.FeedLikeIds;
import org.springframework.data.jpa.repository.JpaRepository;
import java.io.Serializable;

public interface FeedLikeRepository extends JpaRepository<FeedLike, FeedLikeIds> {
}
