package com.sneaker.store.orders.service;

import com.sneaker.store.orders.dto.AddressDTO;
import com.sneaker.store.orders.dto.OrderCreateDTO;
import com.sneaker.store.orders.dto.OrderDTO;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    private OrderService orderService;


    @Test
    void getOrderById_Should_return_dto() {
        Long orderId = 1L;
        String orderNumber = "orderNumber";
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

        OrderDTO actualDTO = orderService.getOrder(orderId);

        assertNotNull(actualDTO);
        assertEquals(expectedDTO, actualDTO);

        verify(orderRepository, times(1)).findById(orderId);
        verify(orderMapper, times(1)).toDTO(order);
    }
}
