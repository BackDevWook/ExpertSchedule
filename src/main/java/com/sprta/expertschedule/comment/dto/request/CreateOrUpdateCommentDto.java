package com.sprta.expertschedule.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateOrUpdateCommentDto {

    @NotBlank
    @Size(min = 1, max = 255)
    private String content; // 댓글 내용

}
