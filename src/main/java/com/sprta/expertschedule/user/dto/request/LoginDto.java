package com.sprta.expertschedule.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginDto {

    // 아이디, 비밀번호 입력

    private String loginId;
    private String password;
}
