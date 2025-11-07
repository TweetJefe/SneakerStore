package com.sneaker.store.orders.contoroller;


import com.sneaker.store.orders.dto.OrderCreateDTO;
import com.sneaker.store.orders.dto.OrderDTO;
import com.sneaker.store.orders.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(
            @Valid @RequestBody OrderCreateDTO dto) {
        service.createOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/get")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable String orderId) {
        return ResponseEntity.ok(service.getOrder(orderId));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable String orderId) {
        return ResponseEntity.ok(service.cancelOrder(orderId));
    }

    @GetMapping("/user/{customerId}/")
    public ResponseEntity<Page<OrderDTO>> getOrdersByCustomerId(@PathVariable Long customerId, Pageable pageable) {
        return ResponseEntity.ok(service.getOrdersByCustomer(customerId, pageable));
    }
}
