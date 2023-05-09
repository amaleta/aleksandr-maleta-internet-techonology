package com.singidunum.delivery.controller;

import com.singidunum.delivery.dto.PaymentDto;
import com.singidunum.delivery.service.PaymentService;
import java.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController extends BaseCrudController<PaymentDto> {
    private final PaymentService service;

    public PaymentController(PaymentService service) {
        super(service);
        this.service = service;
    }


    @GetMapping("/amount-sum-without-date")
    public ResponseEntity getList(
        @RequestParam LocalDate from,
        @RequestParam LocalDate to,
        @RequestParam Long customerId) {
        return new ResponseEntity<>(service.getAmountForDateRange(from,to,customerId), HttpStatus.OK);
    }
}
