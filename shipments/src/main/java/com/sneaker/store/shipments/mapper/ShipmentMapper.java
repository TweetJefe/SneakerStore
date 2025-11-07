package com.sneaker.store.shipments.mapper;

import com.sneaker.store.shipments.dto.ShipmentDTO;
import com.sneaker.store.shipments.model.Shipment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShipmentMapper{
    ShipmentDTO toDTO (Shipment shipment);
    Shipment toEntity (ShipmentDTO shipmentDTO);
}
