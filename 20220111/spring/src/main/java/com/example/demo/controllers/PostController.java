package com.example.demo.controllers;

import java.util.List;

import com.example.demo.dtos.PostDto;
import com.example.demo.models.Post;
import com.example.demo.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDto> list() {
        List<Post> posts = postService.list();
        return posts.stream().map(Post::toDto).toList();
    }

    @GetMapping("/{id}")
    public PostDto detail(
            @PathVariable Long id
    ) {
        Post post = postService.get(id);
        return post.toDto();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(
            @RequestBody @Validated PostDto postDto
    ) {
        Post post = postService.create(postDto);
        return post.toDto();
    }
}
