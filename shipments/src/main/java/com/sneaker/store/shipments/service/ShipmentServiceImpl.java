package com.sneaker.store.shipments.service;

import com.sneaker.store.shipments.dto.ShipmentDTO;
import com.sneaker.store.shipments.enums.Status;
import com.sneaker.store.shipments.exceptions.NullableViolation;
import com.sneaker.store.shipments.exceptions.ServerException;
import com.sneaker.store.shipments.exceptions.UniquenessViolation;
import com.sneaker.store.shipments.mapper.ShipmentMapper;
import com.sneaker.store.shipments.model.Shipment;
import com.sneaker.store.shipments.repository.ShipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService{
    private final ShipmentRepository shipmentRepository;
    private final ShipmentMapper mapper;

    private final String PostgreSQLUniquenessViolation = "23505";
    private final String PostgreSQLNullableViolation = "23502";


    @Override
    public void createShipment(ShipmentDTO dto){
        Shipment shipment = mapper.toEntity(dto);
        saveShipment(shipment);
    }

    @Override
    public void saveShipment(Shipment shipment) {
        try{
            shipmentRepository.save(shipment);
        }catch (DataIntegrityViolationException exception){
            Throwable cause = exception.getCause();
            if(cause instanceof ConstraintViolationException cve){
                String sqlState = cve.getSQLState();
                if(sqlState.equals(PostgreSQLUniquenessViolation)){
                    String constraintName = cve.getConstraintName();
                    throw new UniquenessViolation(constraintName);
                }else if(sqlState.equals(PostgreSQLNullableViolation)){
                    throw new NullableViolation();
                }
            }else{
                throw new ServerException();
            }
        }
    }

    @Override
    public ShipmentDTO getShipmentByOrderId(Long orderId) {
        Shipment shipment = shipmentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Shipment fot the order is not found"));
        return  mapper.toDTO(shipment);
    }

    @Override
    public ShipmentDTO updateStatus (String shipmentNumber, Status status){
        Shipment shipment = shipmentRepository.findByOrderNumber(shipmentNumber)
                .orElseThrow(() -> new EntityNotFoundException("Shipment fot the order is not found"));
        shipment.setStatus(status);
        shipment.setUpdatedAt(LocalDateTime.now());
        saveShipment(shipment);
        return mapper.toDTO(shipment);
    }
}
