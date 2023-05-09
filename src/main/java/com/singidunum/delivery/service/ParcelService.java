package com.singidunum.delivery.service;

import com.singidunum.delivery.dao.entity.ParcelEntity;
import com.singidunum.delivery.dao.repository.ParcelRepository;
import com.singidunum.delivery.dto.ParcelDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ParcelService extends BaseCrudService<ParcelDto, ParcelEntity> {
    private final ParcelRepository repository;

    public ParcelService(
        ModelMapper mapper,
        ParcelRepository repository) {
        super(repository, mapper, ParcelDto.class, ParcelEntity.class);
        this.repository = repository;
    }
}
