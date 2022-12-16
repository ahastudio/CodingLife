package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.modelmapper.ModelMapper;

import com.example.demo.controllers.dtos.ShopDetailDto;
import com.example.demo.controllers.dtos.ShopListDto;
import com.example.demo.exceptions.ShopNotFound;
import com.example.demo.models.Shop;
import com.example.demo.models.ShopId;
import com.example.demo.repositories.ShopRepository;

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
    public ShopDetailDto detail(@PathVariable Long id) {
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
