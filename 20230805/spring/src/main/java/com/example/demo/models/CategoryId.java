package com.example.demo.models;

public class CategoryId extends EntityId {
    private CategoryId() {
        super();
    }

    public CategoryId(String value) {
        super(value);
    }

    public static CategoryId generate() {
        return new CategoryId(newTsid());
    }
}
