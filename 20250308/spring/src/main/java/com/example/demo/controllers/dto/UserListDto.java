package com.example.demo.controllers.dto;

import java.util.List;

public record UserListDto(
    List<UserDto> users
) {
    public record UserDto(
        Long id,
        String name,
        String email
    ) {
    }
}
