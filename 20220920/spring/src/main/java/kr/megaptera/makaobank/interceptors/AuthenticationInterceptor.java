package kr.megaptera.makaobank.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;

import kr.megaptera.makaobank.exceptions.AuthenticationError;
import kr.megaptera.makaobank.models.AccountNumber;
import kr.megaptera.makaobank.utils.JwtUtil;

public class AuthenticationInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;

    public AuthenticationInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            // 토큰 들어온 게 없음.
            return true;
        }

        String accessToken = authorization.substring("Bearer ".length());

        try {
            AccountNumber accountNumber = jwtUtil.decode(accessToken);
            request.setAttribute("accountNumber", accountNumber);
            return true;
        } catch (JWTDecodeException | SignatureVerificationException e) {
            throw new AuthenticationError(e);
        }
    }
}
