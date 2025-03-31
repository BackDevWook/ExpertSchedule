package com.sprta.expertschedule.user.entity;

import com.sprta.expertschedule.BaseEntity;
import com.sprta.expertschedule.user.dto.request.SignUpRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EnableJpaAuditing
public class User extends BaseEntity {

    public User(SignUpRequestDto signUpRequestDto) {
        this.userName = signUpRequestDto.getUserName();
        this.email = signUpRequestDto.getEmail();
        this.loginId = signUpRequestDto.getLoginId();
        this.password = signUpRequestDto.getPassword();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long Id; // 식별자

    @Column(nullable = false, length = 25)
    private String userName; // 유저 이름

    @Column(nullable = false, length = 100)
    private String email; // 이메일

    @Column(nullable = false, unique = true, length = 25)
    private String loginId; // 로그인 ID

    @Column(nullable = false)
    private String password; // 비밀번호

}
