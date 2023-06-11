package com.singidunum.delivery.controller;

import com.singidunum.delivery.dto.PaymentDto;
import com.singidunum.delivery.service.PaymentService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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


    @GetMapping("/amount-sum-between-date")
    public ResponseEntity getList(
        @RequestParam(required = false) String from,
        @RequestParam(required = false) String to) {
        if (from != null && to != null) {
            return new ResponseEntity<>(
                service.getAmountForDateRange(
                    LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"))), HttpStatus.OK);
        }
        return new ResponseEntity<>(List.of(), HttpStatus.OK);
    }
}
