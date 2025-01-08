package com.example.fruit_api.repository;

import com.example.fruit_api.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
