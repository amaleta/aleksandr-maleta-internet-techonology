package com.singidunum.delivery.controller;

import com.singidunum.delivery.dao.entity.ReviewEntity;
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
        @RequestParam Long driverId,
        @RequestParam Integer rating) {
        return service.getDriverReviewGreaterThan(driverId, rating);
    }
}
