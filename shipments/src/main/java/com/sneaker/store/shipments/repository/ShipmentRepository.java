package com.sneaker.store.shipments.repository;


import com.sneaker.store.shipments.dto.ShipmentDTO;
import com.sneaker.store.shipments.model.Shipment;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipmentRepository {

    void save(Shipment shipment);

    Optional<Shipment> findByOrderId(Long orderId);

    Optional<Shipment> findByOrderNumber(String shipmentNumber);
}
