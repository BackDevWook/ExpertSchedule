package com.sprta.expertschedule.schedule.service;

import com.sprta.expertschedule.schedule.dto.request.CreateScheduleDto;
import com.sprta.expertschedule.schedule.dto.request.UpdateScheduleDto;
import com.sprta.expertschedule.schedule.dto.response.ScheduleInfoDto;
import com.sprta.expertschedule.schedule.entity.Schedule;
import com.sprta.expertschedule.schedule.repository.ScheduleRepository;
import com.sprta.expertschedule.user.entity.User;
import com.sprta.expertschedule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 일정 생성
    public void createSchedule(User user, CreateScheduleDto createScheduleDto) {

        scheduleRepository.save(new Schedule(
                user.getUserName(),
                createScheduleDto.getTitle(),
                createScheduleDto.getContent(),
                user
        ));
    }

    // 해당 일정을 만든 사람인지 확인
    public boolean matchScheduleWithUser(String loginId, Long scheduleId) {
        return loginId.equals(scheduleRepository.findById(scheduleId).get().getUser().getLoginId());
    }


    // 로그인 ID로 유저 객체 찾기
    public Optional<User> getUser(String loginId) {
        return userRepository.findByLoginId(loginId);
    }

    // 일정 전체 조회
    public List<ScheduleInfoDto> getAllSchedules() {
        return scheduleRepository.findAll().stream().map(schedule -> new ScheduleInfoDto(
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreateDate(),
                schedule.getUpdateDate()
        )).collect(Collectors.toList());
    }

    // 일정 단일 조회
    public ScheduleInfoDto getSchedule(Long scheduleId) {

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("해당 스케줄 없음"));

        return new ScheduleInfoDto(
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreateDate(),
                schedule.getUpdateDate()
        );
    }

    // 일정 수정
    public void updateSchedule(UpdateScheduleDto updateScheduleDto, Long id, String loginId) {

        // 스케줄 존재 여부 확인 및 타겟팅
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("스케줄 존재 안함"));

        // 해당 일정의 생성자가 맞는지 확인 후 처리
        if(matchScheduleWithUser(loginId, id)) {
            schedule.setTitle(updateScheduleDto.getTitle());
            schedule.setContent(updateScheduleDto.getContent());
            scheduleRepository.save(schedule);
        } else {
            throw new RuntimeException("당신이 주인이 아님");
        }

    }

    // 일정 삭제
    public void deleteSchedule(String loginId, Long id) {

        // 스케줄 존재 여부 확인 및 타겟팅
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("스케줄 존재 안함"));
        // 해당 일정의 생성자가 맞는지 확인 후 처리
        if(matchScheduleWithUser(loginId, id)) {
            scheduleRepository.delete(schedule);
        } else {
            throw new RuntimeException("당신이 주인이 아님");
        }

    }
}
