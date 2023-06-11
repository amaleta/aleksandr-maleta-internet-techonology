package com.singidunum.delivery.controller;

import com.singidunum.delivery.dto.ReviewDto;
import com.singidunum.delivery.service.ReviewService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController extends BaseCrudController<ReviewDto> {
    private final ReviewService service;

    public ReviewController(ReviewService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/get-driver-review-greater-than")
    public List<ReviewDto> getDriverReviewGreaterThan(
        @RequestParam(required = false) Long driverId,
        @RequestParam(required = false) Integer rating) {
        if (driverId != null && rating != null) {
            return service.getDriverReviewGreaterThan(driverId, rating);
        }
        return List.of();
    }
}
