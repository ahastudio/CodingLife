package com.ahastudio.user.application;

import com.ahastudio.user.dtos.UserRegistrationDto;
import com.ahastudio.user.models.User;
import com.ahastudio.user.respositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UserServiceTest {
    @Test
    void create() {
        UserRepository userRepository = mock(UserRepository.class);

        PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

        UserService userService = new UserService(
                userRepository, passwordEncoder);

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(
                "Tester", "tester@example.com", "test");

        User user = userService.create(userRegistrationDto);

        assertThat(user).isNotNull();

        verify(userRepository).save(user);
    }
}
