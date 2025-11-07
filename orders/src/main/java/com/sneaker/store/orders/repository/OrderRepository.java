package com.sneaker.store.orders.repository;

import com.sneaker.store.orders.dto.OrderDTO;
import com.sneaker.store.orders.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(String orderId);
    void save(OrderDTO dto);

    Page<Order> findAllByCustomerId(Long customerId, Pageable pageable);
}
