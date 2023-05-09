package com.singidunum.delivery.dto;

import lombok.Data;

@Data
public class CustomerDto implements BaseDto {
    private Long id;
    private String name;
    private String address;
    private String email;
    private String phone;
}
