package com.example.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.dtos.PostDto;
import com.example.demo.models.Post;
import com.example.demo.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> list() {
        return postRepository.findAll();
    }

    public Post get(Long id) {
        return postRepository.getById(id);
    }

    public Post create(PostDto postDto) {
        Post post = Post.of(postDto);
        return postRepository.save(post);
    }
}
