package com.shop.pay.application;

import com.shop.pay.api.request.CreatePaymentRequestDto;
import com.shop.pay.api.response.PaymentResponseDto;
import com.shop.pay.domain.bank.BankAccount;
import com.shop.pay.domain.bank.BankAccountRepository;
import com.shop.pay.domain.payment.PayStatus;
import com.shop.pay.domain.payment.Payment;
import com.shop.pay.domain.payment.PaymentRepository;
import com.shop.pay.global.error.exception.PaymentException;
import com.shop.pay.infrastructure.kafka.event.PaymentProducerEvent;
import com.shop.pay.infrastructure.kafka.producer.PayEventProducer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.shop.pay.global.error.PaymentErrorCode.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BankAccountRepository bankAccountRepository;
    private final PayEventProducer payEventProducer;
    private final static Long TEMP_MEMBER_ID = 1L;

    @Transactional
    public PaymentResponseDto processPayment(CreatePaymentRequestDto request) {
        validatePayment(request);
        // 고객 계좌 찾기
        BankAccount bankAccount = findBankAccountByMemberId(TEMP_MEMBER_ID);
        createPayment(request, bankAccount);

        return new PaymentResponseDto(PayStatus.COMPLETED.getMessage());
    }

    private void validatePayment(CreatePaymentRequestDto request) {
        if (!PayStatus.isPossiblePay(request.payStatus())) {
            throw new PaymentException(CAN_NOT_PAY);
        }
    }

    private BankAccount findBankAccountByMemberId(Long memberId) {
        return bankAccountRepository.findByMemberId(memberId)
                .orElseThrow(() -> new PaymentException(NOT_FOUND_USER));
    }

    private void createPayment(CreatePaymentRequestDto request, BankAccount bankAccount) {
        try {
            Payment payment = Payment.createPayment(request, bankAccount);
            paymentRepository.save(payment);
            log.info("결제 성공");
            sendEvent("payment-success", request);
        } catch (PaymentException e) {
            log.warn("결제 실패: {}", e.getPaymentErrorCode().getErrorMessage());
            sendEvent("payment-failed", request);
            throw new PaymentException(WITHDRAW_AMOUNT_EXCEEDS_LIMIT);
        }
    }

    private void sendEvent(String topic, CreatePaymentRequestDto request) {
        payEventProducer.send(topic, new PaymentProducerEvent(request.orderId()));
    }
}