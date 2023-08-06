package com.example.demo.dtos.admin;

import java.util.List;

import com.example.demo.models.User;

public record AdminUserListDto(
        List<UserDto> users
) {
    public static AdminUserListDto of(List<User> users) {
        return new AdminUserListDto(
                users.stream()
                        .map(user -> new UserDto(
                                user.id().toString(),
                                user.name(),
                                user.email(),
                                user.role().toString().replace("ROLE_", "")
                        ))
                        .toList()
        );
    }

    public record UserDto(
            String id,
            String name,
            String email,
            String role
    ) {
    }
}
