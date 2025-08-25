package com.green.greengram.application.user;

import com.green.greengram.config.security.SignInProviderType;
import com.green.greengram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUid(String uid);
    User findByUidAndProviderType(String uid, SignInProviderType signInProviderType); //메소드 쿼리

}