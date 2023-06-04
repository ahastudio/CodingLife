package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.User;
import com.example.demo.models.UserId;

public interface UserRepository extends CrudRepository<User, UserId> {
    boolean existsByEmail(String email);
}
