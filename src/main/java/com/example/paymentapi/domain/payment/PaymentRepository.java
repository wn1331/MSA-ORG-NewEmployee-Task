package com.example.paymentapi.domain.payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Boolean existsByOrderId(Long orderId);

    Payment findByOrderId(Long orderId);
}
