package com.singidunum.delivery.controller;

import com.singidunum.delivery.dto.ParcelDto;
import com.singidunum.delivery.service.ParcelService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parcel")
public class ParcelController extends BaseCrudController<ParcelDto> {
    private final ParcelService service;

    public ParcelController(ParcelService service) {
        super(service);
        this.service = service;
    }
}
