package com.sprta.expertschedule.comment.service;

import com.sprta.expertschedule.comment.dto.request.CreateOrUpdateCommentDto;
import com.sprta.expertschedule.comment.dto.response.CommentInfoDto;
import com.sprta.expertschedule.comment.entity.Comment;
import com.sprta.expertschedule.comment.repository.CommentRepository;
import com.sprta.expertschedule.schedule.entity.Schedule;
import com.sprta.expertschedule.schedule.repository.ScheduleRepository;
import com.sprta.expertschedule.user.entity.User;
import com.sprta.expertschedule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;


    // 댓글 생성하기
    public void CreateComment(CreateOrUpdateCommentDto createCommentDto, String loginId, Long scheduleId) {

        User user = userRepository.findByLoginId(loginId).orElseThrow(() -> new RuntimeException("해당 유저 없음"));
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("해당 일정 없음"));

        commentRepository.save(new Comment(createCommentDto.getContent(), user, schedule));

    }

    // 일정 댓글 주인 대조
    public boolean checkCommentUser(String loginId, Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("너 누구임? 주인 불러오셈")).getUser().getLoginId().equals(loginId);
    }

    // 댓글 수정하기
    public void UpdateComment(CreateOrUpdateCommentDto updateDto, Long commentId, String loginId) {
        if (checkCommentUser(loginId, commentId)) {
            Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("댓글이 없는데?"));
            comment.setContent(updateDto.getContent());
            commentRepository.save(comment);
        }
    }

    // 댓글 삭제하기
    public void deleteComment(Long commentId, String loginId) {
        if(checkCommentUser(loginId, commentId)) {
            commentRepository.deleteById(commentId);
        }
    }

    // 한 스케줄에 대해 댓글 전체 조회
    public List<CommentInfoDto> getAllComment(Long scheduleId) {
        return commentRepository.findAll() // 다 찾아서
                .stream() // 스트림 열고
                .filter(comment -> comment.getSchedule().getId().equals(scheduleId)) // 같은 schedule_id 값을 가진 얘를 찾아서
                .map(comment -> new CommentInfoDto(comment.getContent(), comment.getUser().getUserName(), comment.getSchedule().getTitle())) // dto에 넣어서
                .collect(Collectors.toList()); // 리스트로 변환해서 보내기
    }
}
