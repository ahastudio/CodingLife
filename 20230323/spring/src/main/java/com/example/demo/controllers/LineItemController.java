package com.example.demo.controllers;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.cart.AddProductToCartService;
import com.example.demo.application.cart.ChangeCartItemQuantityService;
import com.example.demo.application.cart.GetCartService;
import com.example.demo.controllers.dtos.AddCartLineItemDto;
import com.example.demo.controllers.dtos.CartDto;
import com.example.demo.controllers.dtos.ChangeCartLineItemDto;
import com.example.demo.models.LineItemId;
import com.example.demo.models.ProductId;

@RestController
@RequestMapping("cart-line-items")
public class LineItemController {
    private final GetCartService getCartService;
    private final AddProductToCartService addProductToCartService;
    private final ChangeCartItemQuantityService changeCartItemQuantityService;

    public LineItemController(
            GetCartService getCartService,
            AddProductToCartService addProductToCartService,
            ChangeCartItemQuantityService changeCartItemQuantityService) {
        this.getCartService = getCartService;
        this.addProductToCartService = addProductToCartService;
        this.changeCartItemQuantityService = changeCartItemQuantityService;
    }

    @GetMapping
    public CartDto list() {
        return getCartService.getCartDto();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AddCartLineItemDto dto) {
        ProductId productId = new ProductId(dto.productId());
        int quantity = dto.quantity();

        addProductToCartService.addProduct(productId, quantity);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable("id") String id,
            @RequestBody ChangeCartLineItemDto dto) {
        LineItemId lineItemId = new LineItemId(id);
        int quantity = dto.quantity();

        changeCartItemQuantityService.changeQuantity(lineItemId, quantity);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {
        return "Not Found";
    }
}
