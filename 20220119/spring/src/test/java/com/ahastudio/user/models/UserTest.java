package com.ahastudio.user.models;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    @Test
    void authenticate() {
        PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

        User user = new User();

        user.changePassword(passwordEncoder, "test");

        assertThat(user.authenticate(passwordEncoder, "test")).isTrue();

        assertThat(user.authenticate(passwordEncoder, "xxx")).isFalse();
    }
}
