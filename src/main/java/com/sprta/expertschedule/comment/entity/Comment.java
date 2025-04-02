package com.sprta.expertschedule.comment.entity;

import com.sprta.expertschedule.BaseEntity;
import com.sprta.expertschedule.schedule.entity.Schedule;
import com.sprta.expertschedule.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EnableJpaAuditing
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Comment(String content, User user, Schedule schedule) {
        this.content = content;
        this.user = user;
        this.schedule = schedule;
    }


}
