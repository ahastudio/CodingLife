package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.example.demo.dtos.PostDto;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private String author;

    private String title;

    private String body;

    public Post() {
    }

    public Post(Long id, String author, String title, String body) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.body = body;
    }

    public static Post of(PostDto postDto) {
        return new Post(postDto.getId(), postDto.getAuthor(),
                postDto.getTitle(), postDto.getBody());
    }

    public PostDto toDto() {
        return new PostDto(id, author, title, body);
    }

    public Long id() {
        return id;
    }
}
