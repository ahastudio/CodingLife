package com.example.demo.dtos;

import javax.validation.constraints.NotBlank;

public class PostDto {
    private final Long id;

    @NotBlank
    private final String author;

    @NotBlank
    private final String title;

    @NotBlank
    private final String body;

    public PostDto(Long id, String author, String title, String body) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
