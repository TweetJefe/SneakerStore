package com.sneaker.store.orders.service;

import com.sneaker.store.orders.dto.AddressDTO;
import com.sneaker.store.orders.dto.OrderCreateDTO;
import com.sneaker.store.orders.dto.OrderDTO;
import com.sneaker.store.orders.enums.Status;
import com.sneaker.store.orders.mapper.OrderMapper;
import com.sneaker.store.orders.model.Address;
import com.sneaker.store.orders.model.Order;
import com.sneaker.store.orders.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceUnitTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;
    @InjectMocks
    private OrderServiceImpl orderService;

    Long orderId = 1L;
    String orderNumber = "orderNumber";
    Order order;
    OrderDTO expectedDTO;

    @BeforeEach
    void setUp() {
        List<String> items = List.of("item1", "item2", "item3");

        Order order = new Order();
        order.setId(orderId);
        order.setOrderNumber(orderNumber);
        order.setItems(items);
        order.setQuantity(1);
        order.setOrderDate(LocalDateTime.now());
        order.setName("cName");
        order.setEmail("cEmail");
        order.setPhone("cPhone");
        order.setAddress(new Address());
        order.setStatus(Status.NEW);

        OrderDTO expectedDTO = new OrderDTO
                (orderNumber,
                        items,
                        1,
                        LocalDateTime.now(),
                        "cName",
                        "cEmail",
                        "cPhone",
                        new Address(),
                        Status.NEW
                );
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderMapper.toDTO(order)).thenReturn(expectedDTO);
    }

    @Nested
    class getOrderTests {
        @AfterEach
        void verifiaction() {
            verify(orderRepository).findById(orderId);
        }

        @Test
        void getOrder_ShouldReturnDTO() {
            OrderDTO actualDTO = orderService.getOrder(orderId);

            assertNotNull(actualDTO);
            assertEquals(expectedDTO, actualDTO);
            verify(orderMapper).toDTO(order);
        }

        @Test
        void getOrder_ShouldThrowEntityNotFoundException() {
            when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

            assertThrows(EntityNotFoundException.class, () -> orderService.getOrder(orderId));
            verify(orderMapper, never()).toDTO(any());
        }
    }

    @Nested
    class cancelOrderTests {

        @Test
        void cancelOrder_ShouldReturnDTO() {
            OrderDTO actualDTO = orderService.getOrder(orderId);

            assertNotNull(actualDTO);
            assertEquals(expectedDTO, actualDTO);
            assertEquals(Status.CANCELLED, order.getStatus());

            verify(orderMapper).toDTO(order);
        }

        @Test
        void cancelOrder_ShouldThrowEntityNotFoundException() {
            when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

            assertThrows(EntityNotFoundException.class, () -> orderService.getOrder(orderId));
            verify(orderMapper, never()).toDTO(any());
        }
    }

    @Nested
    class getOrdersByCustomerTests {
        @Test
        void getOrdersByCustomer_ShouldReturnDTO() {
            
        }
    }
}
