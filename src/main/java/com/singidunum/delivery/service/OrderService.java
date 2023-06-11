package com.singidunum.delivery.service;

import com.singidunum.delivery.dao.entity.OrderEntity;
import com.singidunum.delivery.dao.enums.OrderStatus;
import com.singidunum.delivery.dao.repository.OrderRepository;
import com.singidunum.delivery.dto.OrderDto;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends BaseCrudService<OrderDto, OrderEntity> {
    private final OrderRepository repository;
    private final ModelMapper mapper;
    private final CustomerService customerService;
    private final DriverService diverService;
    private final ParcelService parcelService;

    public OrderService(
        ModelMapper mapper,
        OrderRepository repository,
        CustomerService customerService,
        DriverService diverService,
        ParcelService parcelService) {
        super(repository, mapper, OrderDto.class, OrderEntity.class);
        this.repository = repository;
        this.mapper = mapper;
        this.customerService = customerService;
        this.diverService = diverService;
        this.parcelService = parcelService;
    }

    @Override
    public OrderDto create(OrderDto dto) {
        OrderEntity entity = mapper.map(dto, OrderEntity.class);
        entity.setId(dto.getId());
        entity.setCustomer(customerService.findEntityById(dto.getCustomerId()));
        entity.setDriver(diverService.findEntityById(dto.getDriverId()));
        entity.setParcel(parcelService.findEntityById(dto.getParcelId()));
        entity.setStatus(OrderStatus.CREATED.toString());
        return mapper.map(repository.save(entity), OrderDto.class);
    }

    public List<OrderDto> getByStatus(OrderStatus status) {
        return repository.findByStatus(status.toString())
            .stream()
            .map(order -> mapper.map(order, OrderDto.class))
            .toList();
    }


}
