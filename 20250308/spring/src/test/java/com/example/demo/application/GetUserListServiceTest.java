package com.example.demo.application;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.demo.domain.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GetUserListServiceTest {
    @Test
    @DisplayName("getUsers - 사용자 목록을 리턴")
    void getUsers() {
        // Given - Arrange => Fixture(환경, 맥락)
        // SUT와 친구들을 준비
        GetUserListService getUserListService = new GetUserListService();

        // When - Act => 진짜로 실행 (인터페이스)
        List<User> users = getUserListService.getUsers();

        // Then - Assert => 검증
        // expected, actual
        assertEquals(1, users.size());

        // AssertJ의 assertThat
        assertThat(users).hasSize(1);

        User user = users.get(0);
        // 너무 자세히 검사하면 깨지기 쉬운 테스트가 됨.
        // 정말로 중요하고 망가지면 안 되는 걸 중심으로 테스트하는 게 좋음.
        assertThat(user.id()).isEqualTo(1L);
//        assertThat(user.firstName()).isEqualTo("소");
//        assertThat(user.lastName()).isEqualTo("크라테스");
    }
}
