// <Client>
// Authentication -> 로그인 -> Token (인증)
// Authorization <- Token (인가)
//
// <Server>
// Authentication = 로그인 (인증)
// Token -> Authentication (인증)
// User -> Role -> Authorization (인가)

package com.codesoom.demo.interceptors;

import com.codesoom.demo.application.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    private AuthenticationService authenticationService;

    public AuthenticationInterceptor(
            AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        return filterWithPathAndMethod(request) ||
                doAuthentication(request, response);
    }

    private boolean filterWithPathAndMethod(HttpServletRequest request) {
        String path = request.getRequestURI();
        if (!path.startsWith("/products")) {
            return true;
        }

        String method = request.getMethod();
        if (method.equals("GET")) {
            return true;
        }

        return false;
    }

    private boolean doAuthentication(HttpServletRequest request,
                                     HttpServletResponse response)
            throws IOException {
        String authorization = request.getHeader("Authorization");

        if (authorization == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        String accessToken = authorization.substring("Bearer ".length());
        authenticationService.parseToken(accessToken);

        return true;
    }
}
