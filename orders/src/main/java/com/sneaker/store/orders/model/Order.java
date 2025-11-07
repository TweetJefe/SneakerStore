package com.sneaker.store.orders.model;

import com.sneaker.store.orders.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;

    private List<String> items;
    private int quantity;

    private LocalDateTime orderDate;

    private Long customerId;

    private String name;
    private String email;
    private String phone;

    @Embedded
    private Address address;
    
    

    private Status status;
}
