package com.frikiteam.events.domain.repositories;

import com.frikiteam.events.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
