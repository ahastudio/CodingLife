package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.User;
import com.example.demo.models.UserId;

public interface UserRepository extends CrudRepository<User, UserId> {
    List<User> findAllByOrderByIdDesc();

    List<User> findAllByIdIn(List<UserId> ids);

    boolean existsByEmail(String email);
}
