package com.singidunum.delivery.dao.repository;

import com.singidunum.delivery.dao.entity.CarEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    List<CarEntity> findByYearGreaterThanEqual(Integer year);
}
