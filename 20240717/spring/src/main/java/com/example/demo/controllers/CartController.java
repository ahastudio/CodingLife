// 장바구니
// 1. 장바구니 확인 --> GET /cart (lineItems 포함)
// 2. 장바구니에 새 상품 담기 (Product ID, Quantity)
// 3. 장바구니에 이미 있는 상품 담기 --> 수량 변경

package com.example.demo.controllers;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controllers.dtos.CartDto;
import com.example.demo.models.Cart;
import com.example.demo.models.LineItem;
import com.example.demo.repositories.CartRepository;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartRepository cartRepository;

    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @GetMapping
    public CartDto getCart() {
        Cart cart = cartRepository.find();

        List<CartDto.LineItemDto> lineItems =
                IntStream.range(0, cart.lineItemsCount())
                        .mapToObj((index) -> {
                            LineItem lineItem = cart.lineItem(index);
                            return new CartDto.LineItemDto(
                                    lineItem.productId(),
                                    lineItem.quantity()
                            );
                        })
                        .toList();

        return new CartDto(lineItems);
    }
}
