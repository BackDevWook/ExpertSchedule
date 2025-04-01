package com.sprta.expertschedule.schedule.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateScheduleDto {

    private String title; // 제목
    private String content; // 내용
}
