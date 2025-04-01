package com.sprta.expertschedule.schedule.service;

import com.sprta.expertschedule.schedule.dto.request.CreateScheduleDto;
import com.sprta.expertschedule.schedule.entity.Schedule;
import com.sprta.expertschedule.schedule.repository.ScheduleRepository;
import com.sprta.expertschedule.user.entity.User;
import com.sprta.expertschedule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


    // 로그인 ID로 유저 객체 찾기
    public Optional<User> getUser(String loginId) {
        return userRepository.findByLoginId(loginId);
    }
}
