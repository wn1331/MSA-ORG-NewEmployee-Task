package com.example.paymentapi.domain.bank;

import com.example.paymentapi.domain.BaseEntity;
import com.example.paymentapi.global.error.PaymentErrorCode;
import com.example.paymentapi.global.error.exception.PaymentException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BankAccount extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_account_id")
    private Long id;
    private Long memberId;
    private Long deposit;

    private BankAccount(Long memberId, Long deposit) {
        this.memberId = memberId;
        this.deposit = deposit;
    }

    public static BankAccount createAccount(Long memberId, Long deposit) {
        return new BankAccount(memberId, deposit);
    }

    public void withdraw(int totalPrice) {
        if (this.deposit < totalPrice) {
            throw new PaymentException(PaymentErrorCode.WITHDRAW_AMOUNT_EXCEEDS_LIMIT);
        }
        this.deposit -= totalPrice;
    }
}
