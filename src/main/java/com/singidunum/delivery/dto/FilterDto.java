package com.singidunum.delivery.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class FilterDto {
    private String q;
    @Nullable
    private String order;
}
