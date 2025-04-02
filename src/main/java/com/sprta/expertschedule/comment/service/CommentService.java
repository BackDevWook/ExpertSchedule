package com.sprta.expertschedule.comment.service;

import com.sprta.expertschedule.comment.repository.CommentRepository;
import com.sprta.expertschedule.schedule.repository.ScheduleRepository;
import com.sprta.expertschedule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    CommentRepository commentRepository;
    UserRepository userRepository;
    ScheduleRepository scheduleRepository;


}
