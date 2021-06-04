package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.EventInformation;
import com.frikiteam.events.domain.model.Offer;
import com.frikiteam.events.domain.repositories.OfferRepository;
import com.frikiteam.events.domain.service.EventInformationService;
import com.frikiteam.events.domain.service.OfferService;
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
public class OfferServiceImplTest {

    @MockBean
    private OfferRepository offerRepository;

    @Autowired
    private OfferService offerService;

    @TestConfiguration
    static class EventServiceTestConfiguration{
        @Bean
        public OfferService offerService(){
            return new OfferServiceImpl();
        }
    }

    @Test
    void whenGetOfferByIdWithValidIdThenReturnsOffer(){

        //Arrange
        Long id = 1L;
        Offer offer = new Offer();

        offer.setId(id);
        offer.setName("Paulo");
        offer.setDiscountAmount(12.1);


        when(offerRepository.findById(id)).thenReturn(Optional.of(offer));

        //Act
        Offer offer1 = offerService.getOfferById(id);

        //Assert
        assertThat(offer1.getId()).isEqualTo(id);
    }

    @Test
    public void whenGetOfferByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;

        String template = "Resource %s not found for %s with value %s";
        when(offerRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Offer", "id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            Offer offer1  = offerService.getOfferById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeleteOfferByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(offerRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Offer", "id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            ResponseEntity<?> result = offerService.deleteOffer(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeleteOfferByIdWithValidIdThenReturnsResponseEntityOk() {
        //Arrange
        Long id = 1L;
        Offer offer = new Offer();

        offer.setId(id);
        offer.setName("Paulo");
        offer.setDiscountAmount(12.1);

        when(offerRepository.findById(id)).thenReturn(Optional.of(offer));

        //Act
        ResponseEntity<?> result = offerService.deleteOffer(id);

        //Assert
        assertThat(result).isEqualTo(ResponseEntity.ok().build());
    }

    @Test
    public void whenSaveOfferWithValidOrganizerReturnsOffer() {
        //Arrange
        Long id = 1L;
        Offer offer = new Offer();

        offer.setId(id);
        offer.setName("Paulo");
        offer.setDiscountAmount(12.1);

        when(offerRepository.save(offer)).thenReturn(offer);
        // ACt
        Offer result = offerService.saveOffer(offer);
        // Assert
        assertThat(result).isEqualTo(offer);
    }

    @Test
    public void whenUpdateOfferWithValidIdThenReturnsOfferUpdated() {
        // Arrange
        long id = 1, id1 = 13L;

        Offer updateoffer = new Offer();
        Offer oldoffer = new Offer();

        updateoffer.setId(id);
        updateoffer.setName("Paulo");
        updateoffer.setDiscountAmount(12.1);

        oldoffer.setId(id);
        oldoffer.setName("Andres");
        oldoffer.setDiscountAmount(19.1);

        when(offerRepository.findById(id)).thenReturn(Optional.of(oldoffer));
        when(offerRepository.save(updateoffer)).thenReturn(updateoffer);

        // ACt
        Offer offer = offerService.updateOffer(id, updateoffer);

        // Assert
        assertThat(offer).isEqualTo(updateoffer);
    }

    @Test
    public void whenUpdateOfferWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        long id = 1, id1 = 13L;

        Offer updateOffer = new Offer();

        updateOffer.setId(id);
        updateOffer.setName("Paulo");
        updateOffer.setDiscountAmount(12.1);

        when(offerRepository.findById(id)).thenReturn(Optional.empty());
        when(offerRepository.save(updateOffer)).thenReturn(updateOffer);

        String expectedMessage = String.format("Resource %s not found for %s with value %s", "Offer", "id", id);

        // Act
        Throwable exception = catchThrowable(() -> { // capturing exception
            Offer offer = offerService.updateOffer(id,updateOffer);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
