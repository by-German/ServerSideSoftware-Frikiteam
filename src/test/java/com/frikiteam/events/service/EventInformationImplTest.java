package com.frikiteam.events.service;


import com.frikiteam.events.domain.model.EventInformation;
import com.frikiteam.events.domain.model.Organizer;
import com.frikiteam.events.domain.repositories.EventInformationRepository;
import com.frikiteam.events.domain.repositories.EventRepository;
import com.frikiteam.events.domain.service.EventInformationService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EventInformationImplTest {
    @MockBean
    private EventRepository eventRepository;
    @MockBean
    private EventInformationRepository eventInformationRepository;
    @Autowired
    private EventInformationService eventInformationService;

    @TestConfiguration
    static class EventServiceTestConfiguration{
        @Bean
        public EventInformationService eventInformationService(){
            return new EventInformationServiceInpl();
        }
    }

    @Test void whenGetEventInformationByIdWithValidIdThenReturnsEventInformation(){

        //Arrange
        Long id = 1L;
        EventInformation eventInformation = new EventInformation();

        eventInformation.setId(id);
        eventInformation.setDescription("Event about the best films");
        eventInformation.setImage("Favorite Images");


        when(eventInformationRepository.findById(id)).thenReturn(Optional.of(eventInformation));

        //Act
        EventInformation eventInformation1 = eventInformationService.getEventInformationById(id);

        //Assert
        assertThat(eventInformation1.getId()).isEqualTo(id);
    }

    @Test
    public void whenGetEventInformationByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;

        String template = "Resource %s not found for %s with value %s";
        when(eventInformationRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "EventInformation", "id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            EventInformation eventInformation1 = eventInformationService.getEventInformationById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeleteEventInformationByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(eventInformationRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "EventInformation", "id", id);

        // ACt
        Throwable exception = catchThrowable(() -> { // capturing exception
            ResponseEntity<?> result = eventInformationService.deleteEventInformation(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    public void whenDeleteEventInformationByIdWithValidIdThenReturnsResponseEntityOk() {
        // Arrange
        Long id = 1L;
        EventInformation eventInformation = new EventInformation();

        eventInformation.setId(id);
        eventInformation.setDescription("Event about the best films");
        eventInformation.setImage("Favorite Images");

        when(eventInformationRepository.findById(id)).thenReturn(Optional.of(eventInformation));

        //Act
        ResponseEntity<?> result = eventInformationService.deleteEventInformation(id);

        //Assert
        assertThat(result).isEqualTo(ResponseEntity.ok().build());
    }

    @Test
    public void whenSaveEventInformationWithValidOrganizerReturnsEventInformation() {
        // Arrange
        EventInformation eventInformation = new EventInformation();
        eventInformation.setId(1L);
        eventInformation.setDescription("Event about the best films");
        eventInformation.setImage("Favorite Images");

        when(eventInformationRepository.save(eventInformation)).thenReturn(eventInformation);

        // ACt
        EventInformation result = eventInformationService.saveEventInformation(eventInformation);
        // Assert
        assertThat(result).isEqualTo(eventInformation);
    }

    @Test
    public void whenUpdateEventInformationWithValidIdThenReturnsEventInformationUpdated() {
        // Arrange
        long id = 1, id1 = 1;

        EventInformation updateInformation = new EventInformation();
        updateInformation.setId(id);
        updateInformation.setDescription("Event about the best films");
        updateInformation.setImage("Favorite Images");

        EventInformation oldInformation = new EventInformation();
        oldInformation.setId(id1);
        oldInformation.setDescription("Event about the oldest films");
        oldInformation.setImage("Favorite old Images");

        when(eventInformationRepository.findById(id)).thenReturn(Optional.of(oldInformation));
        when(eventInformationRepository.save(updateInformation)).thenReturn(updateInformation);

        // ACt
        EventInformation eventInformation = eventInformationService.updateEventInformation(id, updateInformation);

        // Assert
        assertThat(eventInformation).isEqualTo(updateInformation);
    }

    @Test
    public void whenUpdateEventInformationWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        long id = 1, id1 = 13L;

        EventInformation updateInformation = new EventInformation();

        updateInformation.setId(id);
        updateInformation.setDescription("Event about the best films");
        updateInformation.setImage("Favorite Images");

        when(eventInformationRepository.findById(id)).thenReturn(Optional.empty());
        when(eventInformationRepository.save(updateInformation)).thenReturn(updateInformation);

        String expectedMessage = String.format("Resource %s not found for %s with value %s", "EventInformation", "id", id);

        // Act
        Throwable exception = catchThrowable(() -> { // capturing exception
            EventInformation eventInformation = eventInformationService.updateEventInformation(id, updateInformation);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

}
