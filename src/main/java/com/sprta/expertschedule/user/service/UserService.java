package com.sprta.expertschedule.user.service;

import com.sprta.expertschedule.user.dto.request.SignUpRequestDto;
import com.sprta.expertschedule.user.entity.User;
import com.sprta.expertschedule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

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
            throw new RuntimeException("이 이메일은 이미 사용 중 입니다.");
        } else if(isDuplicateLoginId(signUpRequestDto)) {
            throw new RuntimeException("이 아이디는 이미 사용 중입니다.");
        } else {
            String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
            signUpRequestDto.setPassword(encodedPassword);
            userRepository.save(new User(signUpRequestDto));
        }
    }
}
