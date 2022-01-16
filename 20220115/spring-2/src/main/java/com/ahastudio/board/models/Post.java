package com.ahastudio.board.models;

import com.ahastudio.board.dtos.PostDto;

public class Post {
    private Long id;
    private String author;
    private String title;
    private String body;

    public Post(Long id, String author, String title, String body) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.body = body;
    }

    public Post(Long id, Post other) {
        this.id = id;
        this.author = other.author;
        this.title = other.title;
        this.body = other.body;
    }

    public static Post of(PostDto postDto) {
        return new Post(
                postDto.getId(),
                postDto.getAuthor(),
                postDto.getTitle(),
                postDto.getBody());
    }

    public Long id() {
        return id;
    }

    public PostDto toDto() {
        return new PostDto(id, author, title, body);
    }
}
