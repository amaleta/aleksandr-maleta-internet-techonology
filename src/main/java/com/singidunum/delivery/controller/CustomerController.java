package com.singidunum.delivery.controller;

import com.singidunum.delivery.dto.CustomerDto;
import com.singidunum.delivery.service.CustomerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController extends BaseCrudController<CustomerDto> {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        super(service);
        this.service = service;
    }
}
