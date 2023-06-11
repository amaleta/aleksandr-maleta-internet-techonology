package com.singidunum.delivery.dto;

import com.singidunum.delivery.dao.enums.OrderStatus;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class OrderDto implements BaseDto {
    @Nullable
    private Long id;
    private Long parcelId;
    private Long driverId;
    private Long customerId;
    private String orderDate;
    @Nullable
    private OrderStatus status;
}
