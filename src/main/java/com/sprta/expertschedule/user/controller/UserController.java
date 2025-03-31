package com.sprta.expertschedule.user.controller;

import com.sprta.expertschedule.user.dto.request.SignUpRequestDto;
import com.sprta.expertschedule.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    // 회원가입 요청
    @PostMapping("/register")
    public ResponseEntity<String> signUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        userService.singUp(signUpRequestDto);
        return ResponseEntity.ok("회원가입 성공함");
    }

    // 로그인 요청
}
