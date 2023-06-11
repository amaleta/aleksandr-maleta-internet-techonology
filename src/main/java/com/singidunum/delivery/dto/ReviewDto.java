package com.singidunum.delivery.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class ReviewDto implements BaseDto {
    @Nullable
    private Long id;
    private Long customerId;
    private Long orderId;
    private String reviewText;
    private Long rating;
    private String reviewDate;
}
