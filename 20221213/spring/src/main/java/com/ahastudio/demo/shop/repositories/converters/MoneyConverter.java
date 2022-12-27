package com.ahastudio.demo.shop.repositories.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import com.ahastudio.demo.shop.models.Money;

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, Long> {
    @Override
    public Long convertToDatabaseColumn(Money money) {
        return money.asLong();
    }

    @Override
    public Money convertToEntityAttribute(Long amount) {
        return Money.krw(amount);
    }
}
