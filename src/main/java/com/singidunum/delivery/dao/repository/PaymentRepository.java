package com.singidunum.delivery.dao.repository;

import com.singidunum.delivery.dao.entity.PaymentEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    List<PaymentEntity> findAllByPaymentDateBetween(LocalDate from, LocalDate to);
}
