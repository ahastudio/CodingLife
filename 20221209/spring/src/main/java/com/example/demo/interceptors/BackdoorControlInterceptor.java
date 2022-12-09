package com.example.demo.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class BackdoorControlInterceptor implements HandlerInterceptor {
    @Value("${backdoor.active}")
    private boolean active;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        if (!request.getRequestURI().startsWith("/backdoor/")) {
            return true;
        }

        // 만약 에러를 내고 싶다면 여기서 예외를 던져도 됩니다.

        return active;
    }
}
