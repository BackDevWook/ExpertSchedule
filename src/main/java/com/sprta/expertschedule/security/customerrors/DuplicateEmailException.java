package com.sprta.expertschedule.security.customerrors;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message) {

        super(message);

        // 중복 이메일이 있을시
    }
}
