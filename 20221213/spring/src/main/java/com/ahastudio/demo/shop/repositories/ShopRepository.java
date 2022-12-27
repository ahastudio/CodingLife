package com.ahastudio.demo.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahastudio.demo.shop.models.Shop;
import com.ahastudio.demo.shop.models.ShopId;

public interface ShopRepository extends JpaRepository<Shop, ShopId> {
}
