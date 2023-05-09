package com.singidunum.delivery.service;

import com.singidunum.delivery.dao.entity.CustomerEntity;
import com.singidunum.delivery.dao.repository.CustomerRepository;
import com.singidunum.delivery.dto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends BaseCrudService<CustomerDto, CustomerEntity> {
    private final CustomerRepository repository;
    private final ModelMapper mapper;

    public CustomerService(
        ModelMapper mapper,
        CustomerRepository repository) {
        super(repository, mapper, CustomerDto.class, CustomerEntity.class);
        this.repository = repository;
        this.mapper = mapper;
    }
}
