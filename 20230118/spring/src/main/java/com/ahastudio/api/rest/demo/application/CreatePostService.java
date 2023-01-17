package com.ahastudio.api.rest.demo.application;

import org.springframework.stereotype.Service;

import com.ahastudio.api.rest.demo.dtos.PostDto;
import com.ahastudio.api.rest.demo.models.MultilineText;
import com.ahastudio.api.rest.demo.models.Post;
import com.ahastudio.api.rest.demo.repositories.PostRepository;

@Service
public class CreatePostService {
    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto createPost(PostDto postDto) {
        Post post = new Post(
                postDto.getTitle(),
                MultilineText.of(postDto.getContent())
        );

        postRepository.save(post);

        return new PostDto(post);
    }
}
