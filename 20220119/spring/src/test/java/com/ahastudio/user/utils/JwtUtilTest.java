package com.ahastudio.user.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {
    @Test
    void encodeAndDecode() {
        final Long userId = 1004L;

        JwtUtil jwtUtil = new JwtUtil("MySecret");

        String token = jwtUtil.encode(userId);

        assertThat(token).isNotBlank();
        assertThat(token).contains(".");

        assertThat(jwtUtil.decode(token)).isEqualTo(userId);
    }
}
