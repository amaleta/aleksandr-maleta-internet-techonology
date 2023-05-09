package com.singidunum.delivery.dao.repository;

import com.singidunum.delivery.dao.entity.PaymentEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    List<PaymentEntity> findAllByCustomerIdAndPaymentDateBetween(Long customerId,LocalDate from, LocalDate to);
}
