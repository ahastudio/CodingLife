package com.example.demo.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;

public abstract class ResourceMethodHandler {
    public abstract String handle(String content) throws JsonProcessingException;
}
