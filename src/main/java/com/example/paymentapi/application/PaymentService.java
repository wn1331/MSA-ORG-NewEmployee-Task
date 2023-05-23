package com.example.paymentapi.application;

import com.example.paymentapi.api.request.CreatePaymentRequestDto;
import com.example.paymentapi.api.response.PaymentResponseDto;
import com.example.paymentapi.domain.payment.Payment;
import com.example.paymentapi.domain.payment.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Transactional
    public void createPayment(CreatePaymentRequestDto request){
        if (paymentRepository.existsById(Long.valueOf(request.product()))) throw new RuntimeException("orderID already exists");
        // 예외 발생 //주문번호에 해당하는 결제가 이미 존재하는지 확인. 존재하면 예외 발생

        Payment payment = Payment.createPayment(request);
        paymentRepository.save(payment);

        //PaymentResponseDto 미작성.(뭐를 response 해야 할지 모름..)
    }




}
