package com.shop.pay.domain.payment;

import com.shop.pay.api.request.CreatePaymentRequestDto;
import com.shop.pay.domain.BaseEntity;
import com.shop.pay.domain.bank.BankAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PAYMENTS")
@NoArgsConstructor
@Getter
public class Payment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    private Long orderId;

    private PayStatus payStatus;

    private int totalPrice;

    public static Payment createPayment(CreatePaymentRequestDto dto, BankAccount account) {
        Payment payment = new Payment();
        payment.orderId = dto.orderId();//주문번호
        payment.totalPrice = dto.totalPrice();//총금액
        account.withdraw(dto.totalPrice()); // 계좌에서 total price 만큼 빼줌
        return payment;
    }
}
