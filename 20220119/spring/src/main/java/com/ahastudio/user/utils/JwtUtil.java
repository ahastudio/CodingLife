package com.ahastudio.user.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final Algorithm algorithm;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        algorithm = Algorithm.HMAC256(secret);
    }

    public String encode(Long userId) {
        String token = JWT.create()
                .withClaim("userId", userId)
                .sign(algorithm);
        return token;
    }

    public Long decode(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("userId").asLong();
    }
}
