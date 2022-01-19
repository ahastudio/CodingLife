package com.ahastudio.user.config;

import com.ahastudio.user.interceptors.AuthenticationInterceptor;
import com.ahastudio.user.respositories.UserRepository;
import com.ahastudio.user.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebJavaConfig implements WebMvcConfigurer {
    private UserRepository userRepository;
    private JwtUtil jwtUtil;

    public WebJavaConfig(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor());
    }

    @Bean
    HandlerInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor(userRepository, jwtUtil);
    }
}
