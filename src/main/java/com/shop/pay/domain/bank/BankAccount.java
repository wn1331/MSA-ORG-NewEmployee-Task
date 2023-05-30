package com.shop.pay.domain.bank;

import com.shop.pay.domain.BaseEntity;
import com.shop.pay.global.error.PaymentErrorCode;
import com.shop.pay.global.error.exception.PaymentException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.shop.pay.global.error.PaymentErrorCode.*;

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
            throw new PaymentException(WITHDRAW_AMOUNT_EXCEEDS_LIMIT);
        }
        this.deposit -= totalPrice;
    }
}
