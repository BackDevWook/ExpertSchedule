package com.sprta.expertschedule.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long Id; // 식별자

    @Column(nullable = false, length = 25)
    private String userName; // 유저 이름

    @Column(nullable = false, length = 100)
    private String email; // 이메일

    @Column(nullable = false)
    private LocalDateTime createDate; // 계정 생성일

    @Column(nullable = false)
    private LocalDateTime updateDate; // 계정 수정일

    @Column(nullable = false, unique = true, length = 25)
    @Size(min = 6)
    private String loginId; // 로그인 ID

    @Column(nullable = false, length = 25)
    @Size(min = 8) // 최소 8자 이상
    private String password; // 비밀번호

}
