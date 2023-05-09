package com.singidunum.delivery.service;

import com.singidunum.delivery.dao.entity.DriverEntity;
import com.singidunum.delivery.dao.repository.DriverRepository;
import com.singidunum.delivery.dto.DriverDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DriverService extends BaseCrudService<DriverDto, DriverEntity> {
    private final DriverRepository repository;
    private final ModelMapper mapper;
    private final CarService carService;

    public DriverService(
        ModelMapper mapper,
        DriverRepository repository,
        CarService carService) {
        super(repository, mapper, DriverDto.class, DriverEntity.class);
        this.repository = repository;
        this.mapper = mapper;
        this.carService = carService;
    }

    @Override
    public void create(DriverDto dto) {
        DriverEntity entity = mapper.map(dto, DriverEntity.class);
        entity.setCar(carService.findById(dto.getCarId()));
        repository.save(entity);
    }
}
