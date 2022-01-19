package com.ahastudio.user.interceptors;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ahastudio.user.exceptions.InvalidAccessToken;
import com.ahastudio.user.models.User;
import com.ahastudio.user.respositories.UserRepository;
import com.ahastudio.user.utils.JwtUtil;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthenticationInterceptor implements HandlerInterceptor {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthenticationInterceptor(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            return true;
        }

        if (!authorization.startsWith("Bearer")) {
            throw new InvalidAccessToken();
        }

        String token = authorization.substring("Bearer ".length());

        try {
            Long userId = jwtUtil.decode(token);
            Optional<User> user = userRepository.findById(userId);

            request.setAttribute("user", user);

            return true;
        } catch (SignatureVerificationException exception) {
            throw new InvalidAccessToken(exception);
        }
    }
}
