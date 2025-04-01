package com.sprta.expertschedule.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePasswordDto {

    // 현재 비밀 번호
    private String currentPassword;

    // 변경할 비밀 번호
    @NotBlank
    @Size(min = 8, max = 255)
//    @Pattern()
    private String updatedPassword;
}
