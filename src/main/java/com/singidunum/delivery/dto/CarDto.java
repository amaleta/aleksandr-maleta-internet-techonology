package com.singidunum.delivery.dto;

import lombok.Data;

@Data
public class CarDto implements BaseDto{
    private Long id;
    private String registrationNumber;
    private String model;
    private Integer year;
    private String type;
}
