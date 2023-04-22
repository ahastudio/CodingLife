package com.ahastudio.demo.shop.repositories.converters;

import jakarta.persistence.Converter;

import com.ahastudio.demo.shop.models.ProductId;

@Converter(autoApply = true)
public class ProductIdConverter extends EntityIdConverter<ProductId> {
    @Override
    protected ProductId crateId(String value) {
        return new ProductId(value);
    }
}
