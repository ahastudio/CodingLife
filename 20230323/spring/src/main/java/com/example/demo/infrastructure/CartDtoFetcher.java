package com.example.demo.infrastructure;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.controllers.dtos.CartDto;
import com.example.demo.models.CartId;

@Component
public class CartDtoFetcher {
    private final JdbcTemplate jdbcTemplate;

    public CartDtoFetcher(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CartDto fetchCartDto(CartId cartId) {
        String sql = """
                SELECT
                    *,
                    products.name AS product_name
                FROM line_items
                JOIN products ON line_items.product_id = products.id
                WHERE line_items.cart_id = ?
                ORDER BY line_items.id
                """;

        List<CartDto.LineItemDto> lineItemDtos = jdbcTemplate.query(
                sql,
                (ResultSet resultSet, int rowNum) -> new CartDto.LineItemDto(
                        resultSet.getString("id"),
                        resultSet.getString("product_name"),
                        resultSet.getLong("unit_price"),
                        resultSet.getInt("quantity"),
                        resultSet.getLong("total_price")
                ),
                cartId.toString()
        );

        return new CartDto("test", lineItemDtos);
    }
}
