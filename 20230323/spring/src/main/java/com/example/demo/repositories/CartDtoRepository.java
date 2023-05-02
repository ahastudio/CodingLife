package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.controllers.dtos.CartDto;

public interface CartDtoRepository extends CrudRepository<CartDto, String> {
}
