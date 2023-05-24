package com.example.paymentapi.application;

import com.example.paymentapi.api.request.CreatePaymentRequestDto;
import com.example.paymentapi.api.response.PaymentResponseDto;
import com.example.paymentapi.domain.bank.BankAccount;
import com.example.paymentapi.domain.bank.BankAccountRepository;
import com.example.paymentapi.domain.payment.Payment;
import com.example.paymentapi.domain.payment.PaymentRepository;
import com.example.paymentapi.global.error.PaymentErrorCode;
import com.example.paymentapi.global.error.exception.PaymentException;
import com.example.paymentapi.infrastructure.kafka.event.PaymentProducerEvent;
import com.example.paymentapi.infrastructure.kafka.producer.PayEventProducer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BankAccountRepository bankAccountRepository;
    private final PayEventProducer payEventProducer;
    private final static Long TEST_MEMBER_ID = 1L;

    @Transactional
    public PaymentResponseDto createPayment(CreatePaymentRequestDto request) {
        // 해당 주문이 존재하는지
        if (paymentRepository.existsByOrderId(request.orderId())) {
            throw new PaymentException(PaymentErrorCode.ALL_READY_EXIST_ORDER);
        }

        // 현재는 테스트 단계이므로 변수에 미리 셋팅되어있는 값을 집어 넣음
        BankAccount bankAccount = bankAccountRepository.findByMemberId(TEST_MEMBER_ID)
                .orElseThrow(() -> new PaymentException(PaymentErrorCode.NOT_FOUND_USER));

        try {
            Payment payment = Payment.createPayment(request, bankAccount);
            paymentRepository.save(payment);
            log.info("결제 성공");
        } catch (PaymentException e) {
            log.warn("결제 실패: {}", e.getPaymentErrorCode().getErrorMessage());
            payEventProducer.send("payment failed", new PaymentProducerEvent(request.orderId()));
        }

        return new PaymentResponseDto();
    }
}