package com.sprta.expertschedule.schedule.controller;

import com.sprta.expertschedule.schedule.dto.request.CreateScheduleDto;
import com.sprta.expertschedule.schedule.service.ScheduleService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class SchedulerController {

    private final ScheduleService scheduleService;

    // POST : 일정 생성하기
    @PostMapping
    public ResponseEntity<String> createSchedule(@RequestBody @Valid CreateScheduleDto createScheduleDto, HttpSession session) {

        // 로그인한 사용자 정보 가져오기
        String loginId = session.getAttribute("LOGIN_ID").toString();
        // 로그인하지 않았다면 예외처리
        if(loginId == null) {
            throw new RuntimeException("로그인좀요");
        }

        scheduleService.createSchedule(scheduleService.getUser(loginId).get(), createScheduleDto);

        return ResponseEntity.ok("스케줄 생성 됌");
    }

    // GET : 일정 조회하기


    // GET : 일정 전체 조회하기(페이징)


    // PUT : 일정 수정하기


    // DELETE : 일정 삭제하기



}
