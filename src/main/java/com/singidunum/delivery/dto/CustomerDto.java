package com.singidunum.delivery.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class CustomerDto implements BaseDto {
    @Nullable
    private Long id;
    private String name;
    private String address;
    private String email;
    private String phone;
}
