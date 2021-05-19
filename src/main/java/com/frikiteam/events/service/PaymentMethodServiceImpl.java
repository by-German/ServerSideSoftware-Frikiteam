package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.PaymentMethod;
import com.frikiteam.events.domain.repositories.PaymentMethodRepository;
import com.frikiteam.events.domain.service.PaymentMethodService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;


    @Override
    public Page<PaymentMethod> getAllPaymentMethods(Pageable pageable) {
        return paymentMethodRepository.findAll(pageable);
    }

    @Override
    public PaymentMethod getPaymentMethodById(Long id) {
        return paymentMethodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PaymentMethod", "id", id));
    }

    @Override
    public PaymentMethod savePaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public ResponseEntity<?> deletePaymentMethod(Long id) {
        PaymentMethod existed = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PaymentMethod", "id", id));
        paymentMethodRepository.delete(existed);
        return ResponseEntity.ok().build();
    }

    @Override
    public PaymentMethod updatePaymentMethod(Long id, PaymentMethod paymentMethod) {
        PaymentMethod existed =  paymentMethodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PaymentMethod", "id", id));

        existed.setName(paymentMethod.getName());

        return paymentMethodRepository.save(existed);
    }
}
