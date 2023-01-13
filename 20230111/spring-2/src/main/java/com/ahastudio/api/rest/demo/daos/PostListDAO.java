package com.ahastudio.api.rest.demo.daos;

import java.util.ArrayList;
import java.util.List;

import com.ahastudio.api.rest.demo.dtos.PostDto;
import com.ahastudio.api.rest.demo.exceptions.PostNotFound;

public class PostListDAO implements PostDAO {
    private final List<PostDto> postDtos;

    public PostListDAO() {
        this.postDtos = new ArrayList();
        this.postDtos.add(new PostDto("1", "제목", "테스트입니다"));
        this.postDtos.add(new PostDto("2", "2등", "2등이다!"));
    }

    @Override
    public List<PostDto> findAll() {
        return new ArrayList<>(postDtos);
    }

    @Override
    public PostDto find(String id) {
        return postDtos.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElseThrow(PostNotFound::new);
    }

    @Override
    public void save(PostDto postDto) {
        postDtos.add(postDto);
    }

    @Override
    public void delete(String id) {
        PostDto postDto = find(id);
        postDtos.remove(postDto);
    }
}
