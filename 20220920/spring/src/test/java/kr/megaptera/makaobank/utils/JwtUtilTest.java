package kr.megaptera.makaobank.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.auth0.jwt.exceptions.JWTDecodeException;

import kr.megaptera.makaobank.models.AccountNumber;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JwtUtilTest {
    static final String SECRET = "SECRET";

    JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    void encodeAndDecode() {
        AccountNumber original = new AccountNumber("1234");

        String token = jwtUtil.encode(original);

        assertThat(token).contains(".");

        AccountNumber accountNumber = jwtUtil.decode(token);

        assertThat(accountNumber).isEqualTo(original);
    }

    @Test
    void decodeError() {
        assertThrows(JWTDecodeException.class, () -> {
            jwtUtil.decode("xxx");
        });
    }
}
