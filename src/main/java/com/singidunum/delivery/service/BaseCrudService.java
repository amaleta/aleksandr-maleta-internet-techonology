package com.singidunum.delivery.service;

import com.singidunum.delivery.dao.entity.BaseEntity;
import com.singidunum.delivery.dto.BaseDto;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseCrudService<T extends BaseDto, E extends BaseEntity> {

    private final JpaRepository jpaRepository;
    private final ModelMapper mapper;
    private final Class<T> dtoClass;
    private final Class<E> entityClass;


    public BaseCrudService(
        JpaRepository jpaRepository,
        ModelMapper mapper,
        Class<T> dtoClass,
        Class<E> entityClass) {

        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    public E findById(Long id) {
        return (E) jpaRepository.findById(id).get();
    }

    public List<T> getList() {
        return jpaRepository.findAll().stream()
            .map(entity -> mapper.map(entity, dtoClass))
            .toList();
    }
    public void create(T dto) {
        E entity = mapper.map(dto, entityClass);

        jpaRepository.save(entity);
    }

    public void deleteById(Long id) {
        if (findById(id) != null) jpaRepository.deleteById(id);
    }


}
