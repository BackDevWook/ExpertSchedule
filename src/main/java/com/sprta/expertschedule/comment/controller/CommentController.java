package com.sprta.expertschedule.comment.controller;

import com.sprta.expertschedule.comment.dto.request.CreateOrUpdateCommentDto;
import com.sprta.expertschedule.comment.dto.response.CommentInfoDto;
import com.sprta.expertschedule.comment.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    // GET : 일정의 댓글 전체 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<List<CommentInfoDto>> getAllComment(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(commentService.getAllComment(scheduleId));
    }

    // POST : 댓글 달기
    @PostMapping("/cl/{scheduleId}")
    public ResponseEntity<String> createComment(@PathVariable Long scheduleId,
                                                @RequestBody @Valid CreateOrUpdateCommentDto createCommentDto,
                                                HttpServletRequest request) {
        // 세션에서 로그인ID 가져오기
        HttpSession loginSession = request.getSession(false);
        String loginId = loginSession.getAttribute("LOGIN_ID").toString();

        // 댓글 생성
        commentService.CreateComment(createCommentDto, loginId, scheduleId);

        return ResponseEntity.status(HttpStatus.CREATED).body("댓글 생성 됌");
    }
    // PATCH : 댓글 수정
    @PatchMapping("/cl/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId,
                                                @RequestBody @Valid CreateOrUpdateCommentDto updateDto,
                                                HttpServletRequest request) {

        // 세션에서 로그인 ID 받아오기
        HttpSession loginSession = request.getSession(false);
        String loginId = loginSession.getAttribute("LOGIN_ID").toString();

        // 수정하기
        commentService.UpdateComment(updateDto, commentId, loginId);

        return ResponseEntity.ok("수정 성공함");
    }

    // DELETE : 댓글 삭제
    @DeleteMapping("/cl/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, HttpServletRequest request) {

        // 세션에서 로그인ID 받아오기
        String loginId = request.getSession(false).getAttribute("LOGIN_ID").toString();

        // 삭제하기
        commentService.deleteComment(commentId, loginId);

        return ResponseEntity.ok("삭제됌");
    }

}
