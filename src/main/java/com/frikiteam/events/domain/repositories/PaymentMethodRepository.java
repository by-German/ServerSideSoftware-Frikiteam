package com.frikiteam.events.domain.repositories;

import com.frikiteam.events.domain.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
