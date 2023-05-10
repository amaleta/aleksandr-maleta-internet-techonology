package com.singidunum.delivery.service;

import com.singidunum.delivery.dao.entity.PaymentEntity;
import com.singidunum.delivery.dao.repository.PaymentRepository;
import com.singidunum.delivery.dto.PaymentDto;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PaymentService extends BaseCrudService<PaymentDto, PaymentEntity> {
    private final PaymentRepository repository;
    private final CustomerService customerService;
    private final OrderService orderService;
    private final ModelMapper mapper;


    public PaymentService(
        ModelMapper mapper,
        PaymentRepository repository,
        CustomerService customerService,
        OrderService orderService) {
        super(repository, mapper, PaymentDto.class, PaymentEntity.class);
        this.repository = repository;
        this.customerService = customerService;
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @Override
    public void create(PaymentDto dto) {
        PaymentEntity entity = mapper.map(dto, PaymentEntity.class);
        entity.setCustomer(customerService.findEntityById(dto.getCustomerId()));
        entity.setOrderEntity(orderService.findEntityById(dto.getOrderId()));
        repository.save(entity);
    }

    public String getAmountForDateRange(LocalDate startDate, LocalDate endDate, Long customerId) {
        return String.valueOf(repository.findAllByCustomerIdAndPaymentDateBetween(customerId, startDate, endDate)
            .stream().map(PaymentEntity::getAmount)
            .mapToDouble(BigDecimal::doubleValue)
            .sum());
    }

}
