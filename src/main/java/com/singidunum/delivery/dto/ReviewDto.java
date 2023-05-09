package com.singidunum.delivery.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ReviewDto implements BaseDto {
    private Long id;
    private Long customerId;
    private Long orderId;
    private String reviewText;
    private Integer rating;
    private LocalDate reviewDate;
}
