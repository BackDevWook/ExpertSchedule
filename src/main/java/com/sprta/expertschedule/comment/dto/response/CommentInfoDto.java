package com.sprta.expertschedule.comment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class CommentInfoDto {

    private String content; // 댓글 내용
    private String userName; // 작성자
    private String scheduleName; // 일정 제목


}
