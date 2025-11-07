package com.sneaker.store.orders.service;

import com.sneaker.store.orders.dto.AddressDTO;
import com.sneaker.store.orders.dto.OrderCreateDTO;
import com.sneaker.store.orders.enums.Status;
import com.sneaker.store.orders.mapper.OrderMapper;
import com.sneaker.store.orders.model.Address;
import com.sneaker.store.orders.model.Order;
import com.sneaker.store.orders.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceUnitTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;
    @InjectMocks
    private OrderService orderService;

}
