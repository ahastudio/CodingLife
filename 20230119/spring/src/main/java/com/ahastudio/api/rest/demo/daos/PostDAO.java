package com.ahastudio.api.rest.demo.daos;

import java.util.List;

import com.ahastudio.api.rest.demo.dtos.PostDto;

public interface PostDAO {
    List<PostDto> findAll();

    PostDto find(String id);

    void save(PostDto postDto);

    void delete(String id);
}
