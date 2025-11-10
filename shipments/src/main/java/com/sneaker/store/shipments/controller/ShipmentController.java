package com.sneaker.store.shipments.controller;

import com.sneaker.store.shipments.dto.GetShipmentDTO;
import com.sneaker.store.shipments.dto.ShipmentDTO;
import com.sneaker.store.shipments.enums.Status;
import com.sneaker.store.shipments.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/shipments")
@RequiredArgsConstructor

public class ShipmentController {
    private final ShipmentService service;

    @PostMapping("/create")
    public ResponseEntity<Void> createShipment(@RequestBody ShipmentDTO dto) {
        service.createShipment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{orderNumber}/update-status")
    public ResponseEntity<String> updateShipmentStatus(
            @PathVariable String orderNumber,
            @RequestParam Status status
            ) {
        service.updateStatus(orderNumber, status);
        String message = String.format(
                "Статус заказа %s обновлён на %s в %s",
                orderNumber,
                status,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/order/{orderNumber}")
    public ResponseEntity<GetShipmentDTO> getShipmentByOrder(@PathVariable String orderNumber) {
        service.getShipmentByOrderNumber(orderNumber);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
