package com.sprta.expertschedule.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//회원가입시 요청
@Setter
@Getter
@AllArgsConstructor
public class SignUpRequestDto {

    // 요청 받을 정보
    // 실명, 이메일, 아이디, 비밀번호

    @NotNull
    @Size(max = 25)
    private String userName;

    @NotNull
    @Size(max = 100)
    @Email
    private String email;

    @NotBlank
    @Size(max = 25)
    private String loginId;

    @NotBlank
    @Size(min = 8, max = 255)
    private String password;


}
