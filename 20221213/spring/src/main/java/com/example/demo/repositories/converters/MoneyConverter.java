package com.example.demo.repositories.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import com.example.demo.models.Money;

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
