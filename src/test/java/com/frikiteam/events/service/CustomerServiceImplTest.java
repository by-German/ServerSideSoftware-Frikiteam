package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Customer;
import com.frikiteam.events.domain.repositories.CustomerRepository;
import com.frikiteam.events.domain.service.CustomerService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {
    @MockBean
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    @TestConfiguration
    static class CustomerServiceTestConfiguration {
        @Bean
        public CustomerService customerService() {
            return new CustomerServiceImpl();
        }
    }

    @Test
    public void whenGeCustomerByIdWithValidIdThenReturnsCustomer() {
        // Arrange
        long id = 1;
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName("Alex");
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        // ACt
        Customer foundCustomer = customerService.getCustomerById(id);

        // Assert
        assertThat(foundCustomer.getId()).isEqualTo(id);
    }

    @Test
    public void whenGeCustomerByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        long id = 1;
        String template = "Resource %s not found for %s with value %s";
        when(customerRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Customer", "id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            Customer foundCustomer = customerService.getCustomerById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenSaveCustomerWithValidCustomerReturnsCustomer() {
        // Arrange
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Alex");
        when(customerRepository.save(customer)).thenReturn(customer);

        // ACt
        Customer result = customerService.saveCustomer(customer);

        // Assert
        assertThat(result).isEqualTo(customer);
    }


    @Test
    public void whenDeleteCustomerByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        long id = 1;
        String template = "Resource %s not found for %s with value %s";
        when(customerRepository.findById(id)).thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Customer", "id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            ResponseEntity<?> result = customerService.deleteCustomer(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeleteCustomerByIdWithValidIdThenReturnsResponseEntityOk() {
        // Arrange
        long id = 1;
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName("Alex");
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        // ACt
        ResponseEntity<?> result = customerService.deleteCustomer(id);

        // Assert
        assertThat(result).isEqualTo(ResponseEntity.ok().build());
    }

    @Test
    public void whenUpdateCustomerWithValidIdThenReturnsCustomerUpdated() {
        // Arrange
        long id = 1;
        Customer newCustomer = new Customer();
        newCustomer.setId(id);
        newCustomer.setFirstName("Alex");
        Customer oldCustomer = new Customer();


        
        when(customerRepository.findById(id)).thenReturn(Optional.of(newCustomer));

        // ACt
        ResponseEntity<?> result = customerService.deleteCustomer(id);

        // Assert
        assertThat(result).isEqualTo(ResponseEntity.ok().build());
    }
}