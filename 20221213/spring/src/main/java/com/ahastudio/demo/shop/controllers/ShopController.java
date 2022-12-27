package com.ahastudio.demo.shop.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.modelmapper.ModelMapper;

import com.ahastudio.demo.shop.controllers.dtos.ShopDetailDto;
import com.ahastudio.demo.shop.controllers.dtos.ShopListDto;
import com.ahastudio.demo.shop.exceptions.ShopNotFound;
import com.ahastudio.demo.shop.models.Shop;
import com.ahastudio.demo.shop.models.ShopId;
import com.ahastudio.demo.shop.repositories.ShopRepository;

@RestController
@RequestMapping("/shops")
public class ShopController {
    private final ShopRepository shopRepository;
    private final ModelMapper modelMapper;

    public ShopController(ShopRepository shopRepository,
                          ModelMapper modelMapper) {
        this.shopRepository = shopRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ShopListDto list() {
        List<Shop> shops = shopRepository.findAll();

        return ShopListDto.builder()
                .shops(mapToDtos(shops))
                .build();
    }

    @GetMapping("/{id}")
    public ShopDetailDto detail(@PathVariable String id) {
        Shop shop = shopRepository.findById(new ShopId(id))
                .orElseThrow(ShopNotFound::new);

        return mapToDto(shop);
    }

    @ExceptionHandler(ShopNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String shopNotFound() {
        return "Shop Not Found";
    }

    private List<ShopListDto.Shop> mapToDtos(List<Shop> shops) {
        return shops.stream()
                .map(shop -> modelMapper.map(shop, ShopListDto.Shop.class))
                .toList();
    }

    private ShopDetailDto mapToDto(Shop shop) {
        return modelMapper.map(shop, ShopDetailDto.class);
    }
}
