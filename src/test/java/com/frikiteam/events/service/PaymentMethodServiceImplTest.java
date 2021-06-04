package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Offer;
import com.frikiteam.events.domain.model.PaymentMethod;
import com.frikiteam.events.domain.repositories.OfferRepository;
import com.frikiteam.events.domain.repositories.PaymentMethodRepository;
import com.frikiteam.events.domain.service.OfferService;
import com.frikiteam.events.domain.service.PaymentMethodService;
import com.frikiteam.events.exception.ResourceNotFoundException;
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
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PaymentMethodServiceImplTest {
    @MockBean
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @TestConfiguration
    static class EventServiceTestConfiguration{
        @Bean
        public PaymentMethodService offerService(){
            return new PaymentMethodServiceImpl();
        }
    }

    @Test
    void whenGetPaymentMethodByIdWithValidIdThenReturnsPaymentMethod(){

        //Arrange
        Long id = 1L;
        PaymentMethod paymentMethod = new PaymentMethod();

        paymentMethod.setId(id);
        paymentMethod.setName("Paula");


        when(paymentMethodRepository.findById(id)).thenReturn(Optional.of(paymentMethod));

        //Act
        PaymentMethod paymentMethod1 = paymentMethodService.getPaymentMethodById(id);

        //Assert
        assertThat(paymentMethod1.getId()).isEqualTo(id);
    }

    @Test
    public void whenGetPaymentMethodByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;

        String template = "Resource %s not found for %s with value %s";
        when(paymentMethodRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "PaymentMethod", "id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            PaymentMethod paymentMethod1  = paymentMethodService.getPaymentMethodById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeletePaymentMethodByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(paymentMethodRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "PaymentMethod", "id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            ResponseEntity<?> result = paymentMethodService.deletePaymentMethod(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeletePaymentMethodByIdWithValidIdThenReturnsResponseEntityOk() {
        //Arrange
        Long id = 1L;
        PaymentMethod paymentMethod = new PaymentMethod();

        paymentMethod.setId(id);
        paymentMethod.setName("Paula");

        when(paymentMethodRepository.findById(id)).thenReturn(Optional.of(paymentMethod));

        //Act
        ResponseEntity<?> result = paymentMethodService.deletePaymentMethod(id);

        //Assert
        assertThat(result).isEqualTo(ResponseEntity.ok().build());
    }

    @Test
    public void whenSavePaymentMethodWithValidOrganizerReturnsPaymentMethod() {
        //Arrange
        Long id = 1L;
        PaymentMethod paymentMethod = new PaymentMethod();

        paymentMethod.setId(id);
        paymentMethod.setName("Paula");

        when(paymentMethodRepository.save(paymentMethod)).thenReturn(paymentMethod);
        // ACt
        PaymentMethod result = paymentMethodService.savePaymentMethod(paymentMethod);
        // Assert
        assertThat(result).isEqualTo(paymentMethod);
    }

    @Test
    public void whenUpdatePaymentMethodWithValidIdThenReturnsPaymentMethodUpdated() {
        // Arrange
        long id = 1, id1 = 13L;

        PaymentMethod updatePaymentMethod = new PaymentMethod();
        PaymentMethod oldPaymentMethod = new PaymentMethod();

        updatePaymentMethod.setId(id);
        updatePaymentMethod.setName("Paulo");

        oldPaymentMethod.setId(id);
        oldPaymentMethod.setName("Paula");

        when(paymentMethodRepository.findById(id)).thenReturn(Optional.of(oldPaymentMethod));
        when(paymentMethodRepository.save(updatePaymentMethod)).thenReturn(updatePaymentMethod);

        // ACt
        PaymentMethod paymentMethod = paymentMethodService.updatePaymentMethod(id, updatePaymentMethod);

        // Assert
        assertThat(paymentMethod).isEqualTo(updatePaymentMethod);
    }

    @Test
    public void whenUpdatePaymentMethodrWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        long id = 1, id1 = 13L;

        PaymentMethod updatePaymentMethod = new PaymentMethod();

        updatePaymentMethod.setId(id);
        updatePaymentMethod.setName("Paulo");

        when(paymentMethodRepository.findById(id)).thenReturn(Optional.empty());
        when(paymentMethodRepository.save(updatePaymentMethod)).thenReturn(updatePaymentMethod);

        String expectedMessage = String.format("Resource %s not found for %s with value %s", "PaymentMethod", "id", id);

        // Act
        Throwable exception = catchThrowable(() -> { // capturing exception
            PaymentMethod paymentMethod = paymentMethodService.updatePaymentMethod(id, updatePaymentMethod);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
