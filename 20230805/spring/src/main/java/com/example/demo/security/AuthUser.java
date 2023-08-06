package com.example.demo.security;

import com.example.demo.models.Role;

public record AuthUser(
        String id,
        String email,
        String password,
        String role,
        String accessToken
) {
    public static AuthUser of(
            String id, String email, String password, String role) {
        return new AuthUser(id, email, password, role, "");
    }

    public static AuthUser authenticated(
            String id, String role, String accessToken) {
        return new AuthUser(id, "", "", role, accessToken);
    }

    public boolean isAdmin() {
        return Role.valueOf(role).equals(Role.ROLE_ADMIN);
    }
}
