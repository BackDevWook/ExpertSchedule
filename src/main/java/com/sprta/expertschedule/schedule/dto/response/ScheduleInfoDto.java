package com.sprta.expertschedule.schedule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ScheduleInfoDto {

    private String userName; // 작성자
    private String title; // 제목
    private String content; // 내용
    private LocalDateTime createDateTime; // 작성일
    private LocalDateTime updateDateTime; // 수정일
}
