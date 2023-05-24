package com.example.paymentapi.domain.payment;

import com.example.paymentapi.api.request.CreatePaymentRequestDto;
import com.example.paymentapi.domain.BaseEntity;
import com.example.paymentapi.domain.bank.BankAccount;
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
    private String itemName;
    private int totalPrice;

    public static Payment createPayment(CreatePaymentRequestDto dto, BankAccount account) {
        Payment payment = new Payment();
        payment.orderId = dto.orderId();//주문번호
        payment.itemName = dto.itemName();//상품명
        payment.totalPrice = dto.totalPrice();//총금액

        account.withdraw(dto.totalPrice()); // 계좌에서 total price 만큼 빼줌
        return payment;
    }
}
