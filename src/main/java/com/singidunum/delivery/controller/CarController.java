package com.singidunum.delivery.controller;

import com.singidunum.delivery.dto.CarDto;
import com.singidunum.delivery.service.CarService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/car")
public class CarController extends BaseCrudController<CarDto> {

    private final CarService carService;

    public CarController(CarService carService) {
        super(carService);
        this.carService = carService;
    }

    @GetMapping("/get-by-year-greater-than")
    public ResponseEntity<List<CarDto>> getList(
        @RequestParam String year) {
        log.info("GET /car/get-by-year-greater-than?year={}", year);
        return ResponseEntity.ok()
            .body(carService.getByYearGreaterThan(Integer.valueOf(year)));
    }
}
