package com.example.demo.daos;

import java.util.List;

import com.example.demo.models.Post;

public interface PostDao {
    List<Post> findAll();
}
