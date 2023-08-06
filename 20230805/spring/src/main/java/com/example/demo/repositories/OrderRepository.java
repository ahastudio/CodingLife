package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Order;
import com.example.demo.models.OrderId;
import com.example.demo.models.UserId;

public interface OrderRepository extends CrudRepository<Order, OrderId> {
    List<Order> findAllByUserIdOrderByIdDesc(UserId userId);

    Optional<Order> findByIdAndUserId(OrderId id, UserId userId);
}
