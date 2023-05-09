package com.singidunum.delivery.dao.repository;


import com.singidunum.delivery.dao.entity.DriverEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, Long> {
}