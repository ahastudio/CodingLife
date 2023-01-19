package com.ahastudio.api.rest.demo.application;

import org.springframework.stereotype.Service;

import com.ahastudio.api.rest.demo.dtos.PostDto;
import com.ahastudio.api.rest.demo.models.MultilineText;
import com.ahastudio.api.rest.demo.models.Post;
import com.ahastudio.api.rest.demo.models.PostId;
import com.ahastudio.api.rest.demo.repositories.PostRepository;

@Service
public class UpdatePostService {
    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto updatePost(String id, PostDto postDto) {
        Post post = postRepository.find(PostId.of(id));

        post.update(
                postDto.getTitle(),
                MultilineText.of(postDto.getContent())
        );

        return new PostDto(post);
    }
}
