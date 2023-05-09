package com.singidunum.delivery.dto;

import lombok.Data;

@Data
public class DriverDto implements BaseDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String licenseNumber;
    private Long carId;
}
