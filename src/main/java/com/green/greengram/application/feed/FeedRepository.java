package com.green.greengram.application.feed;

import com.green.greengram.entity.Feed;
import com.green.greengram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}