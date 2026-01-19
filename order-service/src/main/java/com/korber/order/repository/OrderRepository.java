package com.korber.order.repository;

import com.korber.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByProductId(Long productId);

    List<Order> findByStatus(String status);

    List<Order> findByOrderDate(java.time.LocalDate orderDate);
}
