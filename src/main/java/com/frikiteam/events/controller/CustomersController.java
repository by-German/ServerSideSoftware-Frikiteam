package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Customer;
import com.frikiteam.events.domain.service.CustomerService;
import com.frikiteam.events.resource.CustomerResource;
import com.frikiteam.events.resource.SaveCustomerResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get Customers", description = "Get All Customers by Pages", tags = {"customers"})
    @ApiResponse(
            responseCode = "200",
            description = "All Customers returned",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/customers")
    public Page<CustomerResource> getAllCustomers(Pageable pageable){
        Page<Customer> customers = customerService.getAllCustomers(pageable);
        List<CustomerResource> resources = customers.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Customers by id", description = "Get a customer given an id", tags = {"customers"})
    @ApiResponse(
            responseCode = "200",
            description = "Return a customer",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/customers/{id}")
    public CustomerResource getByIdCustomer(@PathVariable Long id){
        return convertToResource(customerService.getCustomerById(id));
    }

    @Operation(summary = "Save a Customer   ", description = "Save a customer", tags = {"customers"})
    @ApiResponse(
            responseCode = "200",
            description = "Return the customer saved",
            content = @Content(mediaType = "application/json")
    )
    @PostMapping("/customers")
    public CustomerResource saveCustomer(@Valid @RequestBody SaveCustomerResource resource) {
        Customer customer = convertToEntity(resource);
        return convertToResource(customerService.saveCustomer(customer));
    }

    @Operation(summary = "Update a Customer", description = "Update a customer given an id and customer body", tags = {"customers"})
    @ApiResponse(
            responseCode = "200",
            description = "Return the customer updated",
            content = @Content(mediaType = "application/json")
    )
    @PutMapping("/customers/{id}")
    public CustomerResource updateCustomer(@PathVariable Long id, @RequestBody SaveCustomerResource resource){
        Customer customer = convertToEntity(resource);
        return convertToResource(customerService.updateCustomer(id, customer));
    }

    @Operation(summary = "Delete a Customer", description = "remove a customer given an id", tags = {"customers"})
    @ApiResponse(
            responseCode = "200",
            description = "Return ResponseEntity",
            content = @Content(mediaType = "application/json")
    )
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
