package com.ahastudio.demo.shop.controllers;

import java.time.LocalDateTime;

import jakarta.transaction.Transactional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backdoor")
@Transactional
public class BackdoorController {
    private final JdbcTemplate jdbcTemplate;

    public BackdoorController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("setup-database")
    public String setupDatabase() {
        jdbcTemplate.execute("DELETE FROM products");
        jdbcTemplate.execute("DELETE FROM shops");

        createShops();
        createProducts();

        return "OK";
    }

    private void createShops() {
        LocalDateTime now = LocalDateTime.now();

        String sql = """
                INSERT INTO shops(
                    id, name, created_at, updated_at
                ) VALUES(?, ?, ?, ?)
                """;

        jdbcTemplate.update(sql, "00010001", "BestShop", now, now);
    }

    private void createProducts() {
        LocalDateTime now = LocalDateTime.now();

        String sql = """
                INSERT INTO products(
                    id, name, price, created_at, updated_at
                ) VALUES(?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(sql, "00020001", "Bread", 10_000L, now, now);
    }
}
