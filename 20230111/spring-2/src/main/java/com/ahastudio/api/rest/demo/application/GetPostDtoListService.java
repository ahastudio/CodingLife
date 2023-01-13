package com.ahastudio.api.rest.demo.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ahastudio.api.rest.demo.dtos.PostDto;
import com.ahastudio.api.rest.demo.models.Post;
import com.ahastudio.api.rest.demo.repositories.PostRepository;

@Service
public class GetPostDtoListService {
    private final PostRepository postRepository;

    public GetPostDtoListService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> getPostDtos() {
        List<Post> posts = postRepository.findAll();

        return posts.stream().map(post -> new PostDto(post)).toList();
    }
}
