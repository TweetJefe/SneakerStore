package com.sneaker.store.shipments.controller;

import com.sneaker.store.shipments.dto.ShipmentDTO;
import com.sneaker.store.shipments.enums.Status;
import com.sneaker.store.shipments.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{shipmentNumber}/update-status")
    public ResponseEntity<Void> updateShipmentStatus(
            @PathVariable String shipmentNumber,
            @RequestParam Status status
            ) {
        service.updateStatus(shipmentNumber, status);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ShipmentDTO> getShipmentByOrder(@PathVariable Long orderId) {
        service.getShipmentByOrderId(orderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
