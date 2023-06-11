package com.singidunum.delivery.service;

import com.singidunum.delivery.dao.entity.BaseEntity;
import com.singidunum.delivery.dto.BaseDto;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

@Slf4j
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

    public T findById(Long id) {
        return mapper.map(jpaRepository.findById(id).get(), dtoClass);
    }

    public E findEntityById(Long id) {
        return (E) jpaRepository.findById(id).get();
    }

    public List<T> getList() {
        return jpaRepository.findAll().stream()
            .map(entity -> mapper.map(entity, dtoClass))
            .toList();
    }

    public T create(T dto) {
        E entity = mapper.map(dto, entityClass);
        entity.setId(dto.getId());
        return mapper.map(jpaRepository.save(entity), dtoClass);
    }

    public void updateById(Long id, T dto) {
        if (jpaRepository.findById(id).isPresent()) {
            E entity = mapper.map(dto, entityClass);
            entity.setId(id);
            jpaRepository.save(entity);
            log.info("Entity with id " + id + " updated");
        } else
            throw new RuntimeException("Entity with id " + id + " does not exist");
    }

    public void deleteById(Long id) {
        if (jpaRepository.findById(id).isPresent()) {
            jpaRepository.deleteById(id);
            log.info("Entity with id " + id + " deleted");
        } else {
            throw new RuntimeException("Entity with id " + id + " does not exist");
        }
    }


}
