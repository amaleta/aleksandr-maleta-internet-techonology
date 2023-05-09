package com.singidunum.delivery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class ParcelDto implements BaseDto {
    private Long id;
    private BigDecimal weight;
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal height;
    private String senderAddress;
    private String recipientAddress;
    private String status;
    private LocalDate deliveryDate;
    private LocalDate sentDate;
}
