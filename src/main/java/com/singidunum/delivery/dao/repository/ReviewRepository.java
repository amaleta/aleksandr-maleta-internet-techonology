package com.singidunum.delivery.dao.repository;

import com.singidunum.delivery.dao.entity.ReviewEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByOrderEntityDriverIdAndRatingGreaterThanEqual(Long driverId, Integer rating);
}
