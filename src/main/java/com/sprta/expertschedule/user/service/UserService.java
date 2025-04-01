package com.sprta.expertschedule.user.service;

import com.sprta.expertschedule.user.dto.request.DeleteUserAccountDto;
import com.sprta.expertschedule.user.dto.request.SignUpRequestDto;
import com.sprta.expertschedule.user.dto.request.UpdatePasswordDto;
import com.sprta.expertschedule.user.dto.response.UserInfoDto;
import com.sprta.expertschedule.user.entity.User;
import com.sprta.expertschedule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder; // 비밀번호 암호화
    private final UserRepository userRepository; // 저장소

    // 중복 여부 검사
    // 1. 로그인 id
    public boolean isDuplicateLoginId(SignUpRequestDto signUpRequestDto) {
        return userRepository.findByLoginId(signUpRequestDto.getLoginId()).isPresent();
    }

    // 2. email
    public boolean isDuplicatedEamil(SignUpRequestDto signUpRequestDto) {
       return userRepository.findByEmail(signUpRequestDto.getEmail()).isPresent();
    }


    // 회원가입
    public void singUp(SignUpRequestDto signUpRequestDto) {
        if(isDuplicatedEamil(signUpRequestDto)) {
            throw new RuntimeException("이 이메일은 이미 사용 중입니다.");
        } else if(isDuplicateLoginId(signUpRequestDto)) {
            throw new RuntimeException("이 아이디는 이미 사용 중입니다.");
        } else {
            String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
            signUpRequestDto.setPassword(encodedPassword);
            userRepository.save(new User(signUpRequestDto));
        }
    }

    // 로그인 아이디로 DB에서 유저를 찾아 비밀번호 검증
    public boolean matchPassword(String loginId, String password) {
        User user = userRepository.findByLoginId(loginId).orElseThrow(() -> new RuntimeException("해당 사용자가 존재하지 않습니다."));
        return passwordEncoder.matches(password, user.getPassword());
    }

    // 비밀번호 변경
    public void updatePassword(String loginId, UpdatePasswordDto updatePasswordDto) {
        // 현재 비밀번호 인증
        if(!matchPassword(loginId, updatePasswordDto.getCurrentPassword())) {
            throw new RuntimeException("비밀번호 틀림");
        }

        // 비밀번호 변경
        User user = userRepository.findByLoginId(loginId).get();
        user.setPassword(passwordEncoder.encode(updatePasswordDto.getUpdatedPassword()));
        userRepository.save(user);
    }

    // 계정 삭제
    public void deleteUserAccount(String loginId, DeleteUserAccountDto deleteUserAccountDto) {
        // 현재 비밀번호 인증
        if(!matchPassword(loginId, deleteUserAccountDto.getPassword())) {
            throw new RuntimeException("비밀번호 틀림");
        }

        // 계정 삭제
        User user = userRepository.findByLoginId(loginId).get();
        userRepository.delete(user);
    }

    // 유저 정보 조회
    public UserInfoDto getUserInfo(String loginId) {
        User user = userRepository.findByLoginId(loginId).orElseThrow(() -> new RuntimeException("해당 유저 없음"));

        return new UserInfoDto(user.getUserName(), user.getCreateDate());
    }

    // 유저 전체 조회
    public List<UserInfoDto> getAllUserInfo() {
        return userRepository.findAll().stream().map(user -> new UserInfoDto(user.getUserName(), user.getCreateDate())).collect(Collectors.toList());
    }

}
