package com.singidunum.delivery.dto;

import com.singidunum.delivery.dao.enums.PaymentMethod;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class PaymentDto implements BaseDto {
    private Long id;
    private Long customerId;
    private Long orderId;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
}
