package com.green.greengram.application.feedcomment;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor

public class FeedCommentService {
    private final FeedCommentRepository feedCommentRepository;

}
