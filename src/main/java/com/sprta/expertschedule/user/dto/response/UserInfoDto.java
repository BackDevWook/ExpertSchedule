package com.sprta.expertschedule.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserInfoDto {

    private String userName; // 사용자 이름
    private LocalDateTime createTime; // 가입 날짜

}
