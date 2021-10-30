package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Customer;
import com.frikiteam.events.domain.model.User;
import com.frikiteam.events.domain.repositories.CustomerRepository;
import com.frikiteam.events.domain.service.CustomerService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public ResponseEntity<?> deleteCustomer(Long id) {
        Customer existed = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        customerRepository.delete(existed);
        return ResponseEntity.ok().build();
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer existed = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        existed.setLogo(customer.getLogo());
        existed.setFirstName(customer.getFirstName());
        existed.setLastName(customer.getLastName());
        existed.setDateBirth(customer.getDateBirth());
        existed.setEmail(customer.getEmail());
        existed.setPassword(customer.getPassword());

        return customerRepository.save(existed);
    }
}
