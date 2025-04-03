package com.sprta.expertschedule.security.customerrors;

public class UnauthorizedScheduleAccessException extends RuntimeException {
    public UnauthorizedScheduleAccessException(String message) {

        super(message);

        // 일정, 댓글 등이 해당 주인의 것이 아닐 때
    }
}
