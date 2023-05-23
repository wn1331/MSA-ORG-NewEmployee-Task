package com.example.paymentapi.domain.payment;

import com.example.paymentapi.api.request.CreatePaymentRequestDto;
import com.example.paymentapi.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PAYMENTS")
@NoArgsConstructor
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="payment_id")
    private Long id;

    private String orderId;
    private int totalPrice;

    public static Payment createPayment(CreatePaymentRequestDto dto) {
        Payment payment = new Payment();
        payment.orderId = dto.product();//주문번호
        payment.totalPrice = dto.paymentInfo();//총금액
        return payment;
    }
}
