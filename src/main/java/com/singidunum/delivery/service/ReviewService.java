package com.singidunum.delivery.service;

import com.singidunum.delivery.dao.entity.ReviewEntity;
import com.singidunum.delivery.dao.repository.ReviewRepository;
import com.singidunum.delivery.dto.ReviewDto;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
        entity.setCustomer(customerService.findEntityById(dto.getCustomerId()));
        entity.setOrderEntity(orderService.findEntityById(dto.getOrderId()));
        entity.setReviewDate(LocalDate.parse(dto.getReviewDate()));
        return mapper.map(repository.save(entity), ReviewDto.class);
    }

    @Override
    public void updateById(Long id, ReviewDto dto) {
        if (repository.findById(id).isPresent()) {
            ReviewEntity entity = mapper.map(dto, ReviewEntity.class);
            entity.setReviewDate(LocalDate.parse(dto.getReviewDate()));
            entity.setCustomer(customerService.findEntityById(dto.getCustomerId()));
            entity.setOrderEntity(orderService.findEntityById(dto.getOrderId()));
            repository.save(entity);
            log.info("Entity with id " + id + " updated");
        } else
            throw new RuntimeException("Entity with id " + id + " does not exist");
    }

    public List<ReviewDto> getDriverReviewGreaterThan(Long driverId, Integer rating) {
        return repository.findByOrderEntityDriverIdAndRatingGreaterThanEqual(driverId, rating)
            .stream()
            .map(review -> mapper.map(review, ReviewDto.class))
            .toList();
    }
}
