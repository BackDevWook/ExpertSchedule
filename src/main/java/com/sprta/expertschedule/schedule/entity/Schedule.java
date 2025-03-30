package com.sprta.expertschedule.schedule.entity;


import com.sprta.expertschedule.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 식별자

    @Column(nullable = false)
    private String userName; // 작성자

    @Column(nullable = false, length = 50)
    private String title; // 일정 제목

    @Column(nullable = false, length = 500)
    private String content; // 내용

    @Column(nullable = false)
    private LocalDateTime createDate; // 작성일

    @Column(nullable = false)
    private LocalDateTime updateDate; // 수정일

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // user 외래키
}
