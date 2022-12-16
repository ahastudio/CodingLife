package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Shop;
import com.example.demo.models.ShopId;

public interface ShopRepository extends JpaRepository<Shop, ShopId> {
}
