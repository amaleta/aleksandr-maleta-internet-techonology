package com.singidunum.delivery.controller;

import com.singidunum.delivery.dto.DriverDto;
import com.singidunum.delivery.service.DriverService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver")
public class DriverController extends BaseCrudController<DriverDto> {
    private final DriverService service;

    public DriverController(DriverService service) {
        super(service);
        this.service = service;
    }
}
