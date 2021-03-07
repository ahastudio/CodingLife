// REST
// 1. Create -> 로그인 => Token
// 2. Read (w/Token) -> 세션 정보 -> 유효한가?, 내 정보
// 3. Update (w/Token) -> Token 재발급
// 4. Delete -> 로그아웃 => Token 무효화
// sessions (복수형) -> session (단수형) 사용.

package com.codesoom.demo.controllers;

import com.codesoom.demo.application.AuthenticationService;
import com.codesoom.demo.dto.SessionRequestData;
import com.codesoom.demo.dto.SessionResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {
    private AuthenticationService authenticationService;

    public SessionController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessionResponseData login(
            @RequestBody SessionRequestData sessionRequestData
    ) {
        String email = sessionRequestData.getEmail();
        String password = sessionRequestData.getPassword();

        String accessToken = authenticationService.login(email, password);

        return SessionResponseData.builder()
                .accessToken(accessToken)
                .build();
    }
}
