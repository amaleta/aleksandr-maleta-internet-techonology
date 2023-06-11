package com.singidunum.delivery.service;

import com.singidunum.delivery.dao.entity.ReviewEntity;
import com.singidunum.delivery.dao.repository.ReviewRepository;
import com.singidunum.delivery.dto.ReviewDto;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ReviewService extends BaseCrudService<ReviewDto, ReviewEntity> {
    private final ReviewRepository repository;
    private final CustomerService customerService;
    private final OrderService orderService;
    private final ModelMapper mapper;

    public ReviewService(
        ModelMapper mapper,
        ReviewRepository repository,
        CustomerService customerService,
        OrderService orderService) {
        super(repository, mapper, ReviewDto.class, ReviewEntity.class);
        this.repository = repository;
        this.customerService = customerService;
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @Override
    public ReviewDto create(ReviewDto dto) {
        ReviewEntity entity = mapper.map(dto, ReviewEntity.class);
        entity.setId(dto.getId());
        entity.setCustomer(customerService.findEntityById(dto.getCustomerId()));
        entity.setOrderEntity(orderService.findEntityById(dto.getOrderId()));
        return mapper.map(repository.save(entity), ReviewDto.class);
    }

    public List<ReviewDto> getDriverReviewGreaterThan(Long driverId, Integer rating) {
        return repository.findByOrderEntityDriverIdAndRatingGreaterThanEqual(driverId, rating)
            .stream()
            .map(review -> mapper.map(review, ReviewDto.class))
            .toList();
    }
}
