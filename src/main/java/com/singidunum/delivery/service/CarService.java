package com.singidunum.delivery.service;



import com.singidunum.delivery.dao.entity.CarEntity;
import com.singidunum.delivery.dao.repository.CarRepository;
import com.singidunum.delivery.dto.CarDto;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CarService extends BaseCrudService<CarDto, CarEntity> {

    private final CarRepository carRepository;
    private final ModelMapper mapper;

    public CarService(
        ModelMapper mapper,
        CarRepository carRepository) {
        super(carRepository, mapper, CarDto.class, CarEntity.class);
        this.carRepository = carRepository;
        this.mapper = mapper;
    }

    public List<CarDto> getByYearGreaterThan(Integer year) {
        return carRepository.findByYearGreaterThanEqual(year)
            .stream()
            .map(car -> mapper.map(car, CarDto.class))
            .toList();
    }

}
