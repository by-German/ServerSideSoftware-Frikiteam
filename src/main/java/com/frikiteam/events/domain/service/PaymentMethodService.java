package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.PaymentMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PaymentMethodService {
    Page<PaymentMethod> getAllPaymentMethods(Pageable pageable);
    PaymentMethod getPaymentMethodById(Long id);
    PaymentMethod savePaymentMethod(PaymentMethod paymentMethod);
    ResponseEntity<?> deletePaymentMethod(Long id);
    PaymentMethod updatePaymentMethod(Long id, PaymentMethod paymentMethod);
}
