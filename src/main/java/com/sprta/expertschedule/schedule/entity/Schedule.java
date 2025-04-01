package com.sprta.expertschedule.schedule.entity;


import com.sprta.expertschedule.BaseEntity;
import com.sprta.expertschedule.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EnableJpaAuditing
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 식별자

    @Column(nullable = false)
    private String userName; // 작성자

    @Column(nullable = false, length = 50)
    private String title; // 일정 제목

    @Column(nullable = false, length = 500)
    private String content; // 내용

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user; // user 외래키

    public Schedule(String userName, String title, String content, User user) {
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.user = user;
    }
}
