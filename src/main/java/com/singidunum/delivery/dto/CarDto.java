package com.singidunum.delivery.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class CarDto implements BaseDto {
    @Nullable
    private Long id;
    private String registrationNumber;
    private String model;
    private Long year;
    private String type;
}
