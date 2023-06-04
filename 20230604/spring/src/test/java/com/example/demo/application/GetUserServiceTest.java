package com.example.demo.application;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.demo.models.User;
import com.example.demo.models.UserId;
import com.example.demo.repositories.UserRepository;

import static com.example.demo.models.Role.ROLE_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetUserServiceTest {
    private UserRepository userRepository;

    private GetUserService getUserService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);

        getUserService = new GetUserService(userRepository);
    }

    @Test
    @DisplayName("gerUser - when the user exists")
    void getUserSuccess() {
        UserId userId = UserId.generate();

        User user = new User(userId, "tester@example.com", "Tester", ROLE_USER);

        given(userRepository.findById(userId)).willReturn(Optional.of(user));

        User found = getUserService.getUser(userId);

        assertThat(found).isEqualTo(user);
    }

    @Test
    @DisplayName("gerUser - when the user doesn't exist")
    void getUserNotFound() {
        UserId userId = UserId.generate();

        given(userRepository.findById(userId)).willReturn(Optional.empty());

        assertThatThrownBy(() -> {
            getUserService.getUser(userId);
        }).isInstanceOf(NoSuchElementException.class);
    }
}
