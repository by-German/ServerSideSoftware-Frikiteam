package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Customer;
import com.frikiteam.events.domain.service.CustomerService;
import com.frikiteam.events.resource.CustomerResource;
import com.frikiteam.events.resource.SaveCustomerResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CustomersController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/customers")
    public Page<CustomerResource> getAllCustomers(Pageable pageable){
        Page<Customer> customers = customerService.getAllCustomers(pageable);
        List<CustomerResource> resources = customers.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/customers/{id}")
    public CustomerResource getByIdCustomer(@PathVariable Long id){
        return convertToResource(customerService.getCustomerById(id));
    }

    @PostMapping("/customers")
    public CustomerResource saveCustomer(@Valid @RequestBody SaveCustomerResource resource) {
        Customer customer = convertToEntity(resource);
        return convertToResource(customerService.saveCustomer(customer));
    }

    @PutMapping("/customers/{id}")
    public CustomerResource updateCustomer(@PathVariable Long id, @RequestBody SaveCustomerResource resource){
        Customer customer = convertToEntity(resource);
        return convertToResource(customerService.updateCustomer(id, customer));
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        return customerService.deleteCustomer(id);
    }

    private Customer convertToEntity(SaveCustomerResource resource) {
        return mapper.map(resource, Customer.class);
    }
    private CustomerResource convertToResource(Customer entity) {
        return mapper.map(entity, CustomerResource.class);
    }

}
