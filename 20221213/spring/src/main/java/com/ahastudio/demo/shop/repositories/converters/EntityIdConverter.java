package com.ahastudio.demo.shop.repositories.converters;

import jakarta.persistence.AttributeConverter;

public abstract class EntityIdConverter<Id>
        implements AttributeConverter<Id, String> {
    @Override
    public String convertToDatabaseColumn(Id id) {
        return id.toString();
    }

    @Override
    public Id convertToEntityAttribute(String value) {
        return crateId(value);
    }

    protected abstract Id crateId(String value);
}
