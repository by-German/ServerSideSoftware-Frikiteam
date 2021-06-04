package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.EventType;
import com.frikiteam.events.domain.repositories.EventRepository;
import com.frikiteam.events.domain.repositories.EventTypeRepository;
import com.frikiteam.events.domain.service.EventTypeService;
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
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EventTypeServiceImplTest {
    @MockBean
    private EventTypeRepository eventTypeRepository;
    @MockBean
    private EventRepository eventRepository;

    @Autowired
    private EventTypeService eventTypeService;

    @TestConfiguration
    static class EventTypeServiceImplTestConfiguration{
        @Bean
        public EventTypeService eventTypeService(){
            return new EventTypeServiceImpl();
        }
    }
    // GOOD
    @Test
    @DisplayName("when_getEventTypeById_withValidID_thenReturnsEventType")
    public void when_getEventTypeById_withValidID_thenReturnsEventType(){
        // Arrange
        long id = 1;
        EventType eventType = new EventType();
        eventType.setId(id);
        eventType.setName("Anime");

        when(eventTypeRepository.findById(id)).thenReturn(Optional.of(eventType));

        // Act
        EventType foundEventType = eventTypeService.getEventTypeById(id);
        // Assert
        assertThat(foundEventType.getId()).isEqualTo(id);
    }
    // BAD
    @Test
    @DisplayName("when_getEventTypeById_withInvalidID_thenReturnsResourceNotFoundException")
    public void when_getEventTypeById_withInvalidID_thenReturnsResourceNotFoundException(){
        // Arrange
        long id = 1;
        String template = "Resource %s not found for %s with value %s";
        when(eventTypeRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "EventType", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> { // capturing exception
            EventType foundEventType = eventTypeService.getEventTypeById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    //GOOD
    @Test
    @DisplayName("when_createEventType_withValidEvent_thenReturnsEventType")
    public void when_createEventType_withValidEvent_thenReturnsEventType(){
        // Arrange
        EventType eventType = new EventType();
        eventType.setId(1L);
        eventType.setName("Anime");
        when(eventTypeRepository.save(eventType)).thenReturn(eventType);

        // ACt
        EventType result = eventTypeService.createEventType(eventType);

        // Assert
        assertThat(result).isEqualTo(eventType);
    }
    // GOOD
    @Test
    @DisplayName("when_updateEventType_withValidID_thenReturnsUpdatedEventType")
    public void when_updateEventType_withValidID_thenReturnsUpdatedEventType(){
        // Arrange
        long id = 1;
        EventType oldEventType = new EventType();
        oldEventType.setId(id);
        oldEventType.setName("Comics");


        EventType updatedEventType = new EventType();
        updatedEventType.setId(id);
        updatedEventType.setName("Anime");

        when(eventTypeRepository.findById(id)).thenReturn(Optional.of(oldEventType));
        when(eventTypeRepository.save(updatedEventType)).thenReturn(updatedEventType);

        // Act
        EventType result = eventTypeService.updateEventType(id, updatedEventType);

        // Assert
        assertThat(result).isEqualTo(updatedEventType);
    }

    // BAD
    @Test
    @DisplayName("when_updateEventType_withInvalidID_thenReturnsUpdatedEventType")
    public void when_updateEventType_withInvalidID_thenReturnsResourceNotFoundException(){
        // Arrange

        long id = 1;
        EventType oldEventType = new EventType();
        oldEventType.setId(id);
        oldEventType.setName("Comics");

        EventType updatedEventType = new EventType();
        updatedEventType.setId(id);
        updatedEventType.setName("Anime");

        String message_template = "Resource %s not found for %s with value %s";
        String expectedMessage = String.format(message_template, "EventType", "Id", id);

        when(eventTypeRepository.findById(id)).thenReturn(Optional.empty());
        when(eventTypeRepository.save(updatedEventType)).thenReturn(updatedEventType);


        // Act
        Throwable exception = catchThrowable(() -> { // capturing exception
            EventType eventType = eventTypeService.updateEventType(id, updatedEventType);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
    // GOOD
    @Test
    @DisplayName("when_deleteEventType_withValidID_thenReturnsResourceNotFoundException")
    public void when_deleteEventType_withValidID_thenReturnsResourceNotFoundException(){
        // Arrange
        long id = 1;
        EventType eventType = new EventType();
        eventType.setId(id);
        eventType.setName("Anime");

        when(eventTypeRepository.findById(id)).thenReturn(Optional.of(eventType));
        // Act
        ResponseEntity<?> result = eventTypeService.deleteEventType(id);
        // Assert
        assertThat(result).isEqualTo(ResponseEntity.ok().build());
    }

    // BAD
    @Test
    @DisplayName("when_deleteEventType_withInvalidID_thenReturnsResponseEntityOk")
    public void when_deleteEventType_withInvalidID_thenReturnsResponseEntityOk(){
        // Arrange
        long id = 1;
        String message_template = "Resource %s not found for %s with value %s";
        String expectedMessage = String.format(message_template, "EventType", "Id", id);

        when(eventTypeRepository.findById(id)).thenReturn(Optional.empty());
        // Act
        Throwable exception = catchThrowable(() -> {
            ResponseEntity<?> result = eventTypeService.deleteEventType(id);
        });
        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
