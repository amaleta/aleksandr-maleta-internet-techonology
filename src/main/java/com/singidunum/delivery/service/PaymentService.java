package com.singidunum.delivery.service;

import com.singidunum.delivery.dao.entity.PaymentEntity;
import com.singidunum.delivery.dao.repository.PaymentRepository;
import com.singidunum.delivery.dto.PaymentDto;
import java.time.LocalDate;
import java.util.List;
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
    public PaymentDto create(PaymentDto dto) {
        PaymentEntity entity = mapper.map(dto, PaymentEntity.class);
        entity.setId(dto.getId());
        entity.setCustomer(customerService.findEntityById(dto.getCustomerId()));
        entity.setOrderEntity(orderService.findEntityById(dto.getOrderId()));
        entity.setPaymentDate(LocalDate.parse(dto.getPaymentDate()));
        return mapper.map(repository.save(entity), PaymentDto.class);
    }

    public List<PaymentDto> getAmountForDateRange(LocalDate startDate, LocalDate endDate) {
        return repository.findAllByPaymentDateBetween(startDate, endDate)
            .stream()
            .map(paymentEntity -> mapper.map(paymentEntity, PaymentDto.class))
            .toList();
    }

}
