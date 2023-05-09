package com.singidunum.delivery.controller;

import com.singidunum.delivery.dao.enums.OrderStatus;
import com.singidunum.delivery.dto.OrderDto;
import com.singidunum.delivery.dto.ReviewDto;
import com.singidunum.delivery.service.OrderService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseCrudController<OrderDto> {
    private final OrderService service;

    public OrderController(OrderService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/get-by-status")
    public List<OrderDto> getDriverReviewGreaterThan(
        @RequestParam OrderStatus status) {
        return service.getByStatus(status);
    }

}
