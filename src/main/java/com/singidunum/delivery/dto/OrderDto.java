package com.singidunum.delivery.dto;

import com.singidunum.delivery.dao.enums.OrderStatus;
import java.time.LocalDate;
import lombok.Data;

@Data
public class OrderDto implements BaseDto {
    private Long id;
    private Long parcelId;
    private Long driverId;
    private Long customerId;
    private LocalDate orderDate;
    private OrderStatus status;
}
