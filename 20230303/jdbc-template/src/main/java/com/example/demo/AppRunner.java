package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class AppRunner implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    public AppRunner(JdbcTemplate jdbcTemplate,
                     TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public void run(String... args) {
        transactionTemplate.execute(status -> {
            String query = "" +
                    "INSERT INTO people (name, age, gender)" +
                    " VALUES (?, ?, ?)";

            jdbcTemplate.update(query, "홍길동", 15, "남");

            throw new RuntimeException("ERROR!");
        });
    }
}
