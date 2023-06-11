package com.singidunum.delivery.dto;

import com.singidunum.delivery.dao.enums.PaymentMethod;
import java.math.BigDecimal;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class PaymentDto implements BaseDto {
    @Nullable
    private Long id;
    private Long customerId;
    private Long orderId;
    private String paymentDate;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
}
