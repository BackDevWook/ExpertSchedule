package com.sprta.expertschedule.schedule.controller;

import com.sprta.expertschedule.schedule.dto.request.CreateScheduleDto;
import com.sprta.expertschedule.schedule.dto.request.UpdateScheduleDto;
import com.sprta.expertschedule.schedule.dto.response.ScheduleInfoDto;
import com.sprta.expertschedule.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class SchedulerController {

    private final ScheduleService scheduleService;

    // POST : 일정 생성하기
    @PostMapping("/cl")
    public ResponseEntity<String> createSchedule(@RequestBody @Valid CreateScheduleDto createScheduleDto, @SessionAttribute(name = "LOGIN_ID") String loginid) {

        scheduleService.createSchedule(scheduleService.getUser(loginid).get(), createScheduleDto);

        return ResponseEntity.ok("스케줄 생성 됌");
    }

    // GET : 일정 조회하기
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleInfoDto> getSchedule(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getSchedule(id));
    }

    // GET : 일정 전체 조회하기(페이징)
    @GetMapping
    public ResponseEntity<List<ScheduleInfoDto>> getSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    // Patch : 일정 수정하기
    @PatchMapping("/cl/{id}")
    public ResponseEntity<String> updateSchedule(@PathVariable Long id, @RequestBody @Valid UpdateScheduleDto updateScheduleDto, @SessionAttribute(name = "LOGIN_ID") String loginid) {

        // 스케줄 수정
        scheduleService.updateSchedule(updateScheduleDto, id, loginid);

        return ResponseEntity.ok("잘 수정 됌");
    }

    // DELETE : 일정 삭제하기
    @DeleteMapping({"/cl/{id}"})
    public ResponseEntity<String> DeleteSchedule(@PathVariable Long id, @SessionAttribute(name = "LOGIN_ID") String loginid) {

        // 스케줄 삭제
        scheduleService.deleteSchedule(loginid, id);

        return ResponseEntity.ok("잘 삭제됌");

    }



}
