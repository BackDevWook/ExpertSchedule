package com.sprta.expertschedule.security.customerrors;

public class DuplicateLoginIdException extends RuntimeException {
    public DuplicateLoginIdException(String message) {
        super(message);
        // 회원가입시 중복 로그인ID가 있을시
    }
}
