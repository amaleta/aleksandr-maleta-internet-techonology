package com.singidunum.delivery.service;

import com.singidunum.delivery.dao.entity.ParcelEntity;
import com.singidunum.delivery.dao.repository.ParcelRepository;
import com.singidunum.delivery.dto.ParcelDto;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParcelService extends BaseCrudService<ParcelDto, ParcelEntity> {
    private final ParcelRepository repository;
    private final ModelMapper mapper;

    public ParcelService(
        ModelMapper mapper,
        ParcelRepository repository) {
        super(repository, mapper, ParcelDto.class, ParcelEntity.class);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ParcelDto create(ParcelDto dto) {
        ParcelEntity entity = mapper.map(dto, ParcelEntity.class);
        entity.setDeliveryDate(LocalDate.parse(dto.getDeliveryDate()));
        entity.setSentDate(LocalDate.parse(dto.getSentDate()));
        repository.save(entity);
        return mapper.map(entity, ParcelDto.class);
    }

    @Override
    public void updateById(Long id, ParcelDto dto) {
        if (repository.findById(id).isPresent()) {
            ParcelEntity entity = mapper.map(dto, ParcelEntity.class);
            entity.setDeliveryDate(LocalDate.parse(dto.getDeliveryDate()));
            entity.setSentDate(LocalDate.parse(dto.getSentDate()));
            repository.save(entity);
            log.info("Entity with id " + id + " updated");
        } else
            throw new RuntimeException("Entity with id " + id + " does not exist");
    }
}
