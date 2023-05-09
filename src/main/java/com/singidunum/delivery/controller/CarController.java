package com.singidunum.delivery.controller;

import com.singidunum.delivery.dto.CarDto;
import com.singidunum.delivery.dto.ReviewDto;
import com.singidunum.delivery.service.CarService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarController extends BaseCrudController<CarDto> {

    private final CarService carService;

    public CarController(CarService carService) {
        super(carService);
        this.carService = carService;
    }

    @GetMapping("/get-by-year-greater-than")
    public List<CarDto> getList(
        @RequestParam Integer year) {
        return carService.getByYearGreaterThan(year);
    }
}
