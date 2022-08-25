package kr.megaptera.makaobank.controllers;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

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

    @Transactional
    @GetMapping("setup-database")
    public String setupDatabase() {
        jdbcTemplate.execute("DELETE FROM account");

        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update("INSERT INTO account" +
                        " (id, account_number, name, amount," +
                        "  created_at, updated_at)" +
                        " VALUES(?, ?, ?, ?, ?, ?)",
                1L, "1234", "Tester", 123_000, now, now);

        return "OK";
    }
}
