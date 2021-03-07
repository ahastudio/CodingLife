package com.codesoom.demo.utils;

import com.codesoom.demo.errors.InvalidTokenException;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JwtUtilTest {
    private static final String SECRET = "12345678901234567890123456789012";

    private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9." +
            "eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";
    private static final String INVALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9." +
            "eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaD0";

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    void encode() {
        String token = jwtUtil.encode(1L);

        assertThat(token).isEqualTo(VALID_TOKEN);
    }

    @Test
    void decodeWithValidToken() {
        Claims claims = jwtUtil.decode(VALID_TOKEN);

        assertThat(claims.get("userId", Long.class)).isEqualTo(1L);
    }

    @Test
    void decodeWithInvalidToken() {
        assertThatThrownBy(() -> jwtUtil.decode(INVALID_TOKEN))
                .isInstanceOf(InvalidTokenException.class);
    }

    @Test
    void decodeWithEmptyToken() {
        assertThatThrownBy(() -> jwtUtil.decode(null))
                .isInstanceOf(InvalidTokenException.class);

        assertThatThrownBy(() -> jwtUtil.decode(""))
                .isInstanceOf(InvalidTokenException.class);

        assertThatThrownBy(() -> jwtUtil.decode("   "))
                .isInstanceOf(InvalidTokenException.class);
    }
}
