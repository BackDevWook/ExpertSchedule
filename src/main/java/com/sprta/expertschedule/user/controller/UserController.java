package com.sprta.expertschedule.user.controller;

import com.sprta.expertschedule.user.dto.request.DeleteUserAccountDto;
import com.sprta.expertschedule.user.dto.request.LoginDto;
import com.sprta.expertschedule.user.dto.request.SignUpRequestDto;
import com.sprta.expertschedule.user.dto.request.UpdatePasswordDto;
import com.sprta.expertschedule.user.dto.response.UserInfoDto;
import com.sprta.expertschedule.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {

        if(!userService.matchPassword(loginDto.getLoginId(), loginDto.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        // 세션
        HttpSession loginSession = request.getSession();
        loginSession.setAttribute("LOGIN_ID", loginDto.getLoginId());
        loginSession.setMaxInactiveInterval(60 * 30); // 30분

        // 쿠키
        Cookie sessionCookie = new Cookie("SESSION_ID", loginSession.getId());
        sessionCookie.setMaxAge(60 * 30); // 30분 유효
        sessionCookie.setPath("/"); // 모든 경로
        sessionCookie.setHttpOnly(true); // 자바스크립트 멈춰!
        response.addCookie(sessionCookie);

        return ResponseEntity.ok("로그인 성공");

    }

    // 로그아웃
    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {

        // 세션 삭제
        HttpSession loginSession = request.getSession(false);
        if(loginSession != null) {
            loginSession.invalidate();
        }

        // 쿠키 삭제
        Cookie sessionCookie = new Cookie("SESSION_ID", null);
        sessionCookie.setMaxAge(0);
        sessionCookie.setPath("/");
        response.addCookie(sessionCookie);

        return ResponseEntity.ok("로그아웃 됌");
    }

    // 비밀번호 변경
    @PatchMapping
    public ResponseEntity<String> updatePassword(@RequestParam String loginId, HttpServletRequest request, @RequestBody @Valid UpdatePasswordDto updatePasswordDto) {

        // 로그인 여부
        HttpSession loginSession = request.getSession(false);
        if(loginSession == null) {
            throw new RuntimeException("로그인 해주셈");
        } else if (loginSession.getAttribute("LOGIN_ID") == null) {
            throw new RuntimeException("로그인 정보가 없음");
        }

        // 비밀번호 인증 후 변경
        userService.updatePassword(loginId, updatePasswordDto);

        return ResponseEntity.ok("비밀번호 변경 성공");
    }

    // 계정 삭제
    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestParam String loginId, HttpServletRequest request, @RequestBody DeleteUserAccountDto deleteUserAccountDto) {

        // 로그인 여부
        HttpSession loginSession = request.getSession(false);
        if(loginSession == null) {
            throw new RuntimeException("로그인 해주셈");
        }

        // 비밀번호 인증 후 삭제
        userService.deleteUserAccount(loginId, deleteUserAccountDto);

        return ResponseEntity.ok("잘 삭제 됌");
    }

    // 유저 조회
    @GetMapping("/{loginId}")
    public ResponseEntity<UserInfoDto> getUserInfo(@PathVariable String loginId) {
        return ResponseEntity.ok(userService.getUserInfo(loginId));
    }

    // 유저 전체 조회
    @GetMapping
    public ResponseEntity<List<UserInfoDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUserInfo());
    }
}
