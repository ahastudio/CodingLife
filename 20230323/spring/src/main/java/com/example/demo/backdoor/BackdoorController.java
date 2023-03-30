package com.example.demo.backdoor;

import java.time.LocalDateTime;

import jakarta.transaction.Transactional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("backdoor")
public class BackdoorController {
    private final JdbcTemplate jdbcTemplate;

    public BackdoorController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("setup-database")
    @Transactional
    public String setupDatabase() {
        deleteLineItems();
        deleteCarts();
        deleteProducts();

        createProducts();

        return "OK!";
    }

    private void deleteLineItems() {
        jdbcTemplate.update("DELETE FROM line_items");
    }

    private void deleteCarts() {
        jdbcTemplate.update("DELETE FROM carts");
    }

    private void deleteProducts() {
        jdbcTemplate.update("DELETE FROM products");
    }

    private void createProducts() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update("""
                        INSERT INTO products (
                            id, name, price, created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?)
                        """,
                "0120000000001", "Product #1", 100_000L, now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO products (
                            id, name, price, created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?)
                        """,
                "0120000000002", "Product #2", 150_000L, now, now
        );
    }
}
