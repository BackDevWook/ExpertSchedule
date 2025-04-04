package com.sprta.expertschedule.security.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
@Component
public class LoginFilter implements Filter {

    // 인증이 필요한 URL
    private static final String[] CHECK_LOGIN_URL = {"/users/cl/**", "/schedules/cl/**", "/comments/cl/**"}; // 앞글자 이름 따서 cl ㅎㅎ

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // 다운 캐스팅
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // URL 따오기
        String requestURL = request.getRequestURI();
        log.info(requestURL);
        log.info("로그인 여부 검사 실행");

        // 검사가 필요한 URL 이면 실행
        if(isCheckLoginURL(requestURL)) {

            // 세션을 가져와서 로그인 상태인지 확인
            HttpSession loginSession = request.getSession(false);

            // 로그인 상태가 아니라면 에러 던지기
            if(loginSession == null || loginSession.getAttribute("LOGIN_ID") == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인 하셈");
                return;
            }
        }

        // 다음 필터 혹은 컨트롤러 호출
        filterChain.doFilter(request, response);
    }

    // 로그인 검사가 필요한 UR1인지 확인
    public boolean isCheckLoginURL(String url) {
        // 대충 ForEach 문으로 받은 url이 체크리스트에 있는지 확인하는 메서드 같은 거라고 보면 됌
        return PatternMatchUtils.simpleMatch(CHECK_LOGIN_URL, url);
    }
}
