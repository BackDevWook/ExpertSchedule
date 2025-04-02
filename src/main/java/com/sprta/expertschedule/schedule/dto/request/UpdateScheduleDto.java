package com.sprta.expertschedule.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class UpdateScheduleDto {


    @Size(max = 50)
    @NotBlank
    private String title; // 제목

    @Size(max = 500)
    @NotBlank
    private String content; // 내용


}
