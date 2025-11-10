package com.sneaker.store.shipments.service;


import com.sneaker.store.shipments.dto.GetShipmentDTO;
import com.sneaker.store.shipments.dto.UpdateShipmentDTO;
import com.sneaker.store.shipments.enums.Status;
import com.sneaker.store.shipments.mapper.ShipmentMapper;
import com.sneaker.store.shipments.model.Shipment;
import com.sneaker.store.shipments.repository.ShipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShipmentServiceUnitTest {
    @Mock
    private ShipmentRepository repository;

    @Mock
    private ShipmentMapper mapper;

    @InjectMocks
    private ShipmentServiceImpl service;

    @Test
    void getShipmentByOrderNumber_Sould_Return_ShipmentDTO() {
        String orderNumber = "orderNumber";

        Shipment shipment = new Shipment();
        shipment.setOrderNumber(orderNumber);
        shipment.setItems(List.of("Air Force 1 white", "Air Jordan 1 Bloodline"));
        shipment.setQuantity(2);

        GetShipmentDTO expectedDTO = new GetShipmentDTO("orderNumber", List.of("Air Force 1 white", "Air Jordan 1 Bloodline"), 2);

        //чтобы не обращаться в рил бд, создаем моки выше и тут настраиваем их работу
        //когда обращаемся к Mock, то thenReturn()
        when(repository.findByOrderNumber(orderNumber)).thenReturn(Optional.of(shipment));
        when(mapper.toGetDTO(shipment)).thenReturn(expectedDTO);

        //Act
        //вызываем работу реального метода
        GetShipmentDTO actualDTO = service.getShipmentByOrderNumber(orderNumber);

        //проверяем, чтобы наш объект не был Null
        assertNotNull(actualDTO);
        //сравниваем объекты, актуал должен быть равен экспектед тк мы настроили мок маппера на возврат экспектед
        assertEquals(expectedDTO, actualDTO);

        //проверяем, что методы были вызваны 1 раз (чтобы проверить не тока результат, но и работу метода)
        verify(repository, times(1)).findByOrderNumber(orderNumber);
        verify(mapper, times(1)).toGetDTO(shipment);
    }

    @Test
    void getShipmentByOrderNumber_whenNotFound_throwsEntityNotFoundException(){
        String orderNumber = "---";

        when(repository.findByOrderNumber(orderNumber)).thenReturn(Optional.empty());

        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () -> service.getShipmentByOrderNumber(orderNumber)
        );

        assertTrue(thrown.getMessage().contains("No Shipment with orderNumber " + orderNumber));

        verify(repository, times(1)).findByOrderNumber(orderNumber);
        verifyNoInteractions(mapper);
    }

    @Test
    void updateShipmentStatus_Sould_Return_ShipmentDTO() {
        String orderNumber = "orderNumber";
        Status status = Status.DELIVERED;

        Shipment shipment = new Shipment();
        shipment.setOrderNumber(orderNumber);
        shipment.setStatus(Status.PENDING);

        UpdateShipmentDTO dto =  new UpdateShipmentDTO(Status.DELIVERED, orderNumber, LocalDateTime.now());

        when(repository.findByOrderNumber(orderNumber)).thenReturn(Optional.of(shipment));
        when(mapper.toUpdateDTO(shipment)).thenReturn(dto);

        UpdateShipmentDTO testDTO = service.updateStatus(orderNumber, status);

        assertNotNull(testDTO);
        assertEquals(dto, testDTO);

        verify(repository, times(1)).findByOrderNumber(orderNumber);
        verify(mapper, times(1)).toUpdateDTO(shipment);
    }
}
