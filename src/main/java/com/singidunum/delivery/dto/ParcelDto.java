package com.singidunum.delivery.dto;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class ParcelDto implements BaseDto {
    @Nullable
    private Long id;
    private BigDecimal weight;
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal height;
    private String senderAddress;
    private String recipientAddress;
    private String deliveryDate;
    private String sentDate;
}
