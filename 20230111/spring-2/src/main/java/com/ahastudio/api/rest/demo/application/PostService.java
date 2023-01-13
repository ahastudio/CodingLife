package com.ahastudio.api.rest.demo.application;

import org.springframework.stereotype.Service;

import com.ahastudio.api.rest.demo.dtos.PostDto;
import com.ahastudio.api.rest.demo.models.MultilineText;
import com.ahastudio.api.rest.demo.models.Post;
import com.ahastudio.api.rest.demo.models.PostId;
import com.ahastudio.api.rest.demo.repositories.PostRepository;

@Service
public class PostService {
    //    private final PostDAO postDAO;
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto getPostDto(String id) {
//        return postDAO.find(id);

        Post post = postRepository.find(PostId.of(id));

        return new PostDto(post);
    }

    public PostDto createPost(PostDto postDto) {
//        String id = TsidCreator.getTsid().toString();
//
//        PostDto newPostDto = new PostDto();
//        newPostDto.setId(id);
//        newPostDto.setTitle(postDto.getTitle());
//        newPostDto.setContent(postDto.getContent());
//
//        postDAO.save(newPostDto);

        Post post = new Post(
                postDto.getTitle(),
                MultilineText.of(postDto.getContent())
        );

        postRepository.save(post);

        return new PostDto(post);
    }

    public PostDto updatePost(String id, PostDto postDto) {
//        PostDto found = postDAO.find(id);
//
//        found.setTitle(postDto.getTitle());
//        found.setContent(postDto.getContent());

        Post post = postRepository.find(PostId.of(id));

        post.update(
                postDto.getTitle(),
                MultilineText.of(postDto.getContent())
        );

        return new PostDto(post);
    }

    public PostDto deletePost(String id) {
//        PostDto postDto = postDAO.find(id);
//
//        postDAO.delete(id);

        Post post = postRepository.find(PostId.of(id));

        postRepository.delete(PostId.of(id));

        return new PostDto(post);
    }
}
