package com.example.demo.infrastructure;

import java.sql.ResultSet;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDetailsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<UserDetails> findByUsername(String username) {
        String query = "SELECT id, password, role FROM users WHERE username=?";

        return jdbcTemplate.query(query, resultSet -> {
            if (!resultSet.next()) {
                return Optional.empty();
            }

            String id = resultSet.getString("id");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");

            UserDetails userDetails = User.withUsername(id)
                    .password(password)
                    .authorities(role)
                    .build();

            return Optional.of(userDetails);
        }, username);
    }

    public void addAccessToken(String userId, String accessToken) {
        jdbcTemplate.update("""
                        INSERT INTO access_tokens (token, user_id)
                        VALUES (?, ?)
                        """,
                accessToken, userId
        );
    }

    public Optional<UserDetails> findByAccessToken(String accessToken) {
        String query = """
                SELECT users.id, users.role
                FROM users
                JOIN access_tokens ON access_tokens.user_id=users.id
                WHERE access_tokens.token=?
                """;

        return jdbcTemplate.query(query, resultSet -> {
            if (!resultSet.next()) {
                return Optional.empty();
            }

            String id = resultSet.getString("id");
            String role = resultSet.getString("role");

            UserDetails userDetails = User.withUsername(id)
                    .password(accessToken)
                    .authorities(role)
                    .build();

            return Optional.of(userDetails);
        }, accessToken);
    }

    public void removeAccessToken(String accessToken) {
        jdbcTemplate.update("""
                        DELETE FROM access_tokens WHERE token=?
                        """,
                accessToken
        );
    }

    public boolean existsByUsername(String username) {
        String query = "SELECT id FROM users WHERE username=?";

        return Boolean.TRUE.equals(
                jdbcTemplate.query(query, ResultSet::next, username));
    }

    public void addUser(String id, String username, String encodedPassword) {
        jdbcTemplate.update("""
                        INSERT INTO users (id, username, password, role)
                        VALUES (?, ?, ?, ?)
                        """,
                id, username, encodedPassword, "ROLE_USER"
        );
    }
}
