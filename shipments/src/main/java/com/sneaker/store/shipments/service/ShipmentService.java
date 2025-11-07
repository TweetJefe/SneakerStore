package com.sneaker.store.shipments.service;


import com.sneaker.store.shipments.dto.ShipmentDTO;
import com.sneaker.store.shipments.enums.Status;
import com.sneaker.store.shipments.model.Shipment;

public interface ShipmentService{
    void createShipment(ShipmentDTO dto);
    void saveShipment(Shipment shipment);

    ShipmentDTO getShipmentByOrderId(Long orderId);
    ShipmentDTO updateStatus(String shipmentNumber, Status status);
}
