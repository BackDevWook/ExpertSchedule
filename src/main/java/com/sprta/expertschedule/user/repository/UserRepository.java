package com.sprta.expertschedule.user.repository;

import com.sprta.expertschedule.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 이메일 찾기
    Optional<User> findByEmail(String email);

    // 로그인 아이디 찾기
    Optional<User> findByLoginId(String loginId);

    // 이름으로 찾기
    Optional<User> findByUserName(String userName);

}
