package com.singidunum.delivery.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class DriverDto implements BaseDto {
    @Nullable
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String licenseNumber;
    private Long carId;
}
