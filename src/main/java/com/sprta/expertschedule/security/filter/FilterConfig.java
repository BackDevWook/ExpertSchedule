package com.sprta.expertschedule.security.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {


    @Bean
    public FilterRegistrationBean filterLogin() { // 로그인 인증
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>(); //
        filterRegistrationBean.setFilter(new LoginFilter()); // 필터 등록
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("abcd"); // 이름
        filterRegistrationBean.setOrder(1); // 첫번째로

        return filterRegistrationBean;
    }
}
