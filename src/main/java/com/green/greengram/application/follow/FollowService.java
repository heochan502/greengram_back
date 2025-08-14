package com.green.greengram.application.follow;


import com.green.greengram.config.model.UserPrincipal;
import com.green.greengram.entity.User;
import com.green.greengram.entity.UserFollow;
import com.green.greengram.entity.UserFollowIds;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowService{
    private final FollowRepository followRepository;

    public void postUserFollow(Long fromUserId, Long toUserId) {
       UserFollowIds userFollowIds = UserFollowIds.builder()
               .fromUserId(fromUserId)
               .toUserId(toUserId)
               .build();
//        userFollowIds.setFromUserId(fromUserId);
//        userFollowIds.setToUserId(toUserId);
//        log.info("id {}, {}", userFollowIds.getFromUserId(), userFollowIds.getToUserId());
        UserFollow userFollow = new UserFollow();
        userFollow.setUserFollowIds(userFollowIds);
        userFollow.userFollow();
//
//        User fromUser = new User();
//        fromUser.setUserId(fromUserId);
//        User toUser = new User();
//        toUser.setUserId(toUserId);
//
//        userFollow.setFromUser(fromUser);
//        userFollow.setToUser(toUser);
//        log.info("여기여기여기id {}, {}", userFollow.getFromUser(), userFollow.getToUser());
        followRepository.save(userFollow);


    }
}
