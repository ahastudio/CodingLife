package com.example.demo.repositories;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.dtos.CartDto;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles("test")
class CartDtoRepositoryTest {
    @Autowired
    private CartDtoRepository cartDtoRepository;

    @Test
    void test() {
        String id = "test";

        // add product to cart
        // 끝나면 CartDto 새로 만든다.

        CartDto.LineItemDto lineItem = new CartDto.LineItemDto(
                "line-item-01", "Product #1", 10_000, 2, 20_000);

        CartDto cartDto = new CartDto(id, List.of(lineItem));

        cartDtoRepository.save(cartDto);

        CartDto found = cartDtoRepository.findById(id).get();

        // 여기서는 그냥 간단히 toString으로 확인하지만… 🤔
        assertThat(found.toString()).contains(
                "CartDto{id='test'/, lineItems=[LineItemDto[id=line-item-01");
    }
}
