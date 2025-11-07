package com.sneaker.store.shipments.dto;

import com.sneaker.store.shipments.model.Address;

import java.time.LocalDateTime;
import java.util.List;

public record ShipmentDTO(
        List<String> items,
        int quantity,
        String shipmentNumber,
        String recipientName,
        String recipientEmail,
        Address address,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deliveredAt,
        LocalDateTime estimatedDeliveryDate,
        String notes
) {
}
