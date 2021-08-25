package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.Customer;
import com.frikiteam.events.domain.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    Page<Customer> getAllCustomers(Pageable pageable);
    Customer getCustomerById(Long id);
    Customer saveCustomer(Customer customer);
    ResponseEntity<?>  deleteCustomer(Long id);
    Customer updateCustomer(Long id, Customer customer);

    // rules of business
    // Customer getCustomerByEmail(String email);
}
