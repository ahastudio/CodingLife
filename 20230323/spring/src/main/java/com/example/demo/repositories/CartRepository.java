package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Cart;
import com.example.demo.models.CartId;

public interface CartRepository extends CrudRepository<Cart, CartId> {
}
