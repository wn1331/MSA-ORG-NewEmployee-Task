package com.example.paymentapi.api.error;

import com.example.paymentapi.global.error.PaymentErrorResponse;
import com.example.paymentapi.global.error.exception.PaymentException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PaymentExceptionHandler {
    @ExceptionHandler(PaymentException.class)
    public PaymentErrorResponse notFoundOrder(HttpServletRequest request, PaymentException e){
        return new PaymentErrorResponse(e.getPaymentErrorCode(), e.getMessage());
    }
}
