package com.example.demo.backdoor;

import java.time.LocalDateTime;

import jakarta.transaction.Transactional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backdoor")
public class BackdoorController {
    private final JdbcTemplate jdbcTemplate;

    public BackdoorController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/setup-database")
    @Transactional
    public String setupDatabase() {
        deleteImages();
        deleteProductOptionItems();
        deleteProductOptions();
        deleteProducts();
        deleteCategories();

        createCategories();
        createProducts();
        createProductOptions();
        createProductOptionItems();
        createImages();

        return "OK!";
    }

    private void deleteCategories() {
        jdbcTemplate.update("DELETE FROM categories");
    }

    private void deleteProducts() {
        jdbcTemplate.update("DELETE FROM products");
    }

    private void deleteProductOptions() {
        jdbcTemplate.update("DELETE FROM product_options");
    }

    private void deleteProductOptionItems() {
        jdbcTemplate.update("DELETE FROM product_option_items");
    }

    private void deleteImages() {
        jdbcTemplate.update("DELETE FROM images");
    }

    private void createCategories() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update("""
                        INSERT INTO categories (
                            id, name, created_at, updated_at)
                        VALUES (?, ?, ?, ?)
                        """,
                "0BV000CAT0001", "top", now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO categories (
                            id, name, created_at, updated_at)
                        VALUES (?, ?, ?, ?)
                        """,
                "0BV000CAT0002", "outer", now, now
        );
    }

    private void createProducts() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update("""
                        INSERT INTO products (
                            id, category_id, name, price, description,
                            created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?, ?, ?)
                        """,
                "0BV000PRO0001", "0BV000CAT0001",
                "맨투맨", 128_000L, "편하게 입을 수 있는 맨투맨",
                now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO products (
                            id, category_id, name, price, description,
                            created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?, ?, ?)
                        """,
                "0BV000PRO0002", "0BV000CAT0001",
                "셔츠", 118_000L, "편합니다",
                now, now
        );
    }

    private void createProductOptions() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update("""
                        INSERT INTO product_options (
                            id, product_id, name, created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000OPT0001", "0BV000PRO0001", "색상", now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO product_options (
                            id, product_id, name, created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000OPT0002", "0BV000PRO0001", "사이즈", now, now
        );
    }

    private void createProductOptionItems() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update("""
                        INSERT INTO product_option_items (
                            id, product_option_id, name, created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000ITEM001", "0BV000OPT0001", "Black", now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO product_option_items (
                            id, product_option_id, name, created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000ITEM002", "0BV000OPT0001", "White", now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO product_option_items (
                            id, product_option_id, name, created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000ITEM003", "0BV000OPT0002", "S", now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO product_option_items (
                            id, product_option_id, name, created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000ITEM004", "0BV000OPT0002", "M", now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO product_option_items (
                            id, product_option_id, name, created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000ITEM005", "0BV000OPT0002", "L", now, now
        );
    }

    private void createImages() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update("""
                        INSERT INTO images (
                            id, product_id, url, created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000IMG0001", "0BV000PRO0001",
                "https://ahastudio.github.io/my-image-assets/images/cbcl-products/01.jpg",
                now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO images (
                            id, product_id, url, created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000IMG0002", "0BV000PRO0002",
                "https://ahastudio.github.io/my-image-assets/images/cbcl-products/02.jpg",
                now, now
        );
    }
}
