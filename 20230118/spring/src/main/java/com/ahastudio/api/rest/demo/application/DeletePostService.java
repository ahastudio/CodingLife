package com.ahastudio.api.rest.demo.application;

import org.springframework.stereotype.Service;

import com.ahastudio.api.rest.demo.dtos.PostDto;
import com.ahastudio.api.rest.demo.models.Post;
import com.ahastudio.api.rest.demo.models.PostId;
import com.ahastudio.api.rest.demo.repositories.PostRepository;

@Service
public class DeletePostService {
    private final PostRepository postRepository;

    public DeletePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto deletePost(String id) {
        Post post = postRepository.find(PostId.of(id));

        postRepository.delete(PostId.of(id));

        return new PostDto(post);
    }
}
