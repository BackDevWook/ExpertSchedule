package com.sprta.expertschedule.security.customerrors;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {

        super(message);

        // 비밀번호 인증이 실패하였을 때
    }
}
