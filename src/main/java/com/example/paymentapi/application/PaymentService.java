package com.example.paymentapi.application;

import com.example.paymentapi.api.request.CreatePaymentRequestDto;
import com.example.paymentapi.api.response.PaymentResponseDto;
import com.example.paymentapi.domain.bank.BankAccount;
import com.example.paymentapi.domain.bank.BankAccountRepository;
import com.example.paymentapi.domain.payment.PayStatus;
import com.example.paymentapi.domain.payment.Payment;
import com.example.paymentapi.domain.payment.PaymentRepository;
import com.example.paymentapi.global.error.exception.PaymentException;
import com.example.paymentapi.infrastructure.kafka.event.PaymentProducerEvent;
import com.example.paymentapi.infrastructure.kafka.producer.PayEventProducer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.paymentapi.domain.payment.PayStatus.COMPLETED;
import static com.example.paymentapi.global.error.PaymentErrorCode.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BankAccountRepository bankAccountRepository;
    private final PayEventProducer payEventProducer;
    private final static Long TEST_MEMBER_ID = 1L;

    @Transactional
    public PaymentResponseDto processPayment(CreatePaymentRequestDto request) {
        validatePayment(request);
        // 고객 계좌 찾기
        BankAccount bankAccount = findBankAccountByMemberId(TEST_MEMBER_ID);
        createPayment(request, bankAccount);

        return new PaymentResponseDto(COMPLETED.getMessage());
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
            sendEvent("payment-success", request);
            log.info("결제 성공");
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