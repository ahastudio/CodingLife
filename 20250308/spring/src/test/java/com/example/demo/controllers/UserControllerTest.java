package com.example.demo.controllers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.demo.application.GetUserListService;
import com.example.demo.controllers.dto.UserListDto;
import com.example.demo.controllers.testdouble.GetUserListServiceSpy;
import com.example.demo.controllers.testdouble.GetUserListServiceStub;
import com.example.demo.domain.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UserControllerTest {
    @Test
    @DisplayName("GET /users - Stub 예제")
        // 의존성을 통해 얻는 주어진 데이터가 있을 때 어떤 결과가 나오는가?
        // 테스트의 안정성을 높이고 속도를 높여줌.
    void listWithStub() {
        // Given
        // Stub 역할
        GetUserListService getUserListService = new GetUserListServiceStub();
        UserController userController = new UserController(getUserListService);

        // When
        UserListDto body = userController.list();

        // Then
        assertThat(body.users()).hasSize(1);
    }

    @Test
    @DisplayName("GET /users - Mock/Spy 예제")
        // 의존성을 잘 쓰고 있는가?
    void listWithSpy() {
        // Given
        // Mock/Spy 역할
        GetUserListServiceSpy getUserListService = new GetUserListServiceSpy();
        UserController userController = new UserController(getUserListService);

        // When
        UserListDto body = userController.list();

        // Then
        assertThat(getUserListService.getUsersWasCalled()).isTrue();
    }

    @Test
    @DisplayName("GET /users - Mockito 사용")
    public void listWithMockito() {
        // Given
        GetUserListService getUserListService = mock(GetUserListService.class);
        given(getUserListService.getUsers()).willReturn(List.of(
            new User(1L, "소", "크라테스", "test@example.com")));
//        when(getUserListService.getUsers()).thenReturn(List.of(
//            new User(1L, "소", "크라테스", "test@example.com")));
        UserController userController = new UserController(getUserListService);

        // When
        UserListDto body = userController.list();

        // Then
        assertThat(body.users()).hasSize(1);

        verify(getUserListService).getUsers();
    }
}
