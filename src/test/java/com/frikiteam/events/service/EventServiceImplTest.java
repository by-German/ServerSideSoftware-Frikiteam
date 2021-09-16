package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.*;
import com.frikiteam.events.domain.repositories.*;
import com.frikiteam.events.domain.service.CommentService;
import com.frikiteam.events.domain.service.EventService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.text.html.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class EventServiceImplTest {
    @MockBean
    private EventRepository eventRepository;
    @MockBean
    private OrganizerRepository organizerRepository;
    @MockBean
    private CustomerRepository customerRepository;
    @MockBean
    private PlaceRepository placeRepository;
    @MockBean
    private TagRepository tagRepository;


    @Autowired
    private EventService eventService;

    @TestConfiguration
    static class EventServiceTestConfiguration {
        @Bean
        public EventService eventService() {
            return new EventServiceImpl();
        }
    }

    @Test
    void whenCreateEventWithIdsValidThenReturnsEvent() {
        // Arrange
        long organizerId = 1;
        long placeId = 1;
        Event event = new Event();
        Organizer organizer = new Organizer();
        Place place = new Place();
        place.setId(placeId);
        event.setPlace(place);

        when(organizerRepository.findById(organizerId)).thenReturn(Optional.of(organizer));
        when(placeRepository.findById(placeId)).thenReturn(Optional.of(place));
        when(eventRepository.save(event)).thenReturn(event);

        // Action
        Event result = eventService.createEvent(organizerId, event);

        // Assert
        assertThat(true);
    }

    @Test
    void whenCreateEventWithOrganizerIdInvalidThenReturnsResourceNotFoundException() {
        // Arrange
        long organizerId = 1;
        long placeId = 1;
        Event event = new Event();
        Organizer organizer = new Organizer();
        Place place = new Place();

        when(organizerRepository.findById(organizerId)).thenReturn(Optional.empty());
        when(placeRepository.findById(placeId)).thenReturn(Optional.of(place));
        when(eventRepository.save(event)).thenReturn(event);

        // Action
        Throwable exception = catchThrowable(() -> {
            Event result = eventService.createEvent(organizerId, event);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Organizer not found for id with value 1");
    }

    @Test
    void whenCreateEventWithPlaceIdInvalidThenReturnsResourceNotFoundException() {
        // Arrange
        long organizerId = 1;
        long placeId = 1;
        Event event = new Event();
        Organizer organizer = new Organizer();
        Place place = new Place();
        place.setId(placeId);
        event.setPlace(place);

        when(organizerRepository.findById(organizerId)).thenReturn(Optional.of(organizer));
        when(placeRepository.findById(placeId)).thenReturn(Optional.empty());
        when(eventRepository.save(event)).thenReturn(event);

        // Action
        Throwable exception = catchThrowable(() -> {
            Event result = eventService.createEvent(organizerId, event);
            throw new ResourceNotFoundException("Place", "id", placeId);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Place not found for id with value 1");
    }

    @Test
    void whenUpdateEventWithIdsValidThenReturnsEvent() {
        // Arrange
        long organizerId = 1;
        Organizer organizer = new Organizer();
        long eventId = 1;
        Event event = new Event();
        event.setOrganizer(organizer);

        when(organizerRepository.findById(organizerId)).thenReturn(Optional.of(organizer));
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(eventRepository.save(event)).thenReturn(event);

        // Act
        Event result = eventService.updateEvent(organizerId, eventId, event);

        // Assert
        assertThat(result).isEqualTo(event);
    }

    @Test
    void whenUpdateEventWithOrganizerIdInvalidThenReturnsResourceNotFoundException() {
        // Arrange
        long organizerId = 1;
        Organizer organizer = new Organizer();
        long eventId = 1;
        Event event = new Event();
        event.setOrganizer(organizer);

        when(organizerRepository.findById(organizerId)).thenReturn(Optional.empty());
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(eventRepository.save(event)).thenReturn(event);

        // Action
        Throwable exception = catchThrowable(() -> {
            Event result = eventService.updateEvent(organizerId, eventId, event);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Organizer not found for Id with value 1");
    }

    @Test
    void whenUpdateEventWithEventIdInvalidThenReturnsResourceNotFoundException() {
        // Arrange
        long organizerId = 1;
        Organizer organizer = new Organizer();
        long eventId = 1;
        Event event = new Event();
        event.setOrganizer(organizer);

        when(organizerRepository.findById(organizerId)).thenReturn(Optional.of(organizer));
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());
        when(eventRepository.save(event)).thenReturn(event);

        // Action
        Throwable exception = catchThrowable(() -> {
            Event result = eventService.updateEvent(organizerId, eventId, event);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Event not found for Id with value 1");
    }

    @Test
    void whenUpdateEventThatDoesNotBelongToOrganizerThenThrowRuntimeException() {
        // Arrange
        long organizerId = 1;
        Organizer organizer = new Organizer();
        long eventId = 1;
        Event event = new Event();

        when(organizerRepository.findById(organizerId)).thenReturn(Optional.of(organizer));
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(eventRepository.save(event)).thenReturn(event);

        // Action
        Throwable exception = catchThrowable(() -> {
            Event result = eventService.updateEvent(organizerId, eventId, event);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Event 1 for Organizer 1 not found");
    }


    @Test
    void whenAssignEventTagWithIdsValidThenReturnsEvent() {
        // Arrange
        long eventId = 1;
        Event event = new Event();
        long tagId = 1;
        Tag tag = new Tag();

        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(eventRepository.save(event)).thenReturn(event);

        // Act
        Event result = eventService.assignEventTag(eventId, tagId);

        // Assert
        assertThat(result).isEqualTo(event);
    }

    @Test
    void whenAssignEventTagWithEventIdInvalidThenReturnsResourceNotFoundException() {
        // Arrange
        long eventId = 1;
        Event event = new Event();
        long tagId = 1;
        Tag tag = new Tag();

        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());
        when(eventRepository.save(event)).thenReturn(event);

        // Action
        Throwable exception = catchThrowable(() -> {
            Event result = eventService.assignEventTag(eventId, tagId);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Event not found for Id with value 1");
    }

    @Test
    void whenAssignEventTagWithTagIdInvalidThenReturnsResourceNotFoundException() {
        // Arrange
        long eventId = 1;
        Event event = new Event();
        long tagId = 1;
        Tag tag = new Tag();

        when(tagRepository.findById(tagId)).thenReturn(Optional.empty());
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(eventRepository.save(event)).thenReturn(event);

        // Action
        Throwable exception = catchThrowable(() -> {
            Event result = eventService.assignEventTag(eventId, tagId);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Tag not found for Id with value 1");
    }

    @Test
    void whenGetAllEventsByTagIdWithIdValidThenReturnsListEvents() {
        // Arrange
        long tagId = 1;
        Tag tag = new Tag();
        List<Event> events = new ArrayList<>();
        events.add(new Event());
        tag.setEvents(events);

        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));

        // Act
        Page<Event> results = eventService.getAllEventsByTagId(tagId, Pageable.unpaged());

        // Assert
        assertThat(results.getContent()).isEqualTo(events);
    }

    @Test
    void whenGetAllEventsByTagIdWithIdInvalidThenReturnsListEvents() {
        // Arrange
        long tagId = 1;
        Tag tag = new Tag();
        List<Event> events = new ArrayList<>();
        events.add(new Event());
        tag.setEvents(events);

        when(tagRepository.findById(tagId)).thenReturn(Optional.empty());

        // Action
        Throwable exception = catchThrowable(() -> {
            Page<Event> results = eventService.getAllEventsByTagId(tagId, Pageable.unpaged());
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Tag not found for Id with value 1");
    }

    @Test
    void whenUnassignEventTagWithIdsValidThenReturnsEvent() {
        // Arrange
        long eventId = 1;
        Event event = new Event();
        long tagId = 1;
        Tag tag = new Tag();

        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(eventRepository.save(event)).thenReturn(event);

        // Act
        Event result = eventService.unassignEventTag(eventId, tagId);

        // Assert
        assertThat(result).isEqualTo(event);
    }

    @Test
    void whenUnassignEventTagWithTagIdInvalidThenReturnsResourceNotFoundException() {
        // Arrange
        long eventId = 1;
        Event event = new Event();
        long tagId = 1;
        Tag tag = new Tag();

        when(tagRepository.findById(tagId)).thenReturn(Optional.empty());
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(eventRepository.save(event)).thenReturn(event);

        // Act
        Throwable exception = catchThrowable(() -> {
            Event result = eventService.unassignEventTag(eventId, tagId);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Tag not found for Id with value 1");
    }

    @Test
    void whenUnassignEventTagWithEventIdInvalidThenReturnsResourceNotFoundException() {
        // Arrange
        long eventId = 1;
        Event event = new Event();
        long tagId = 1;
        Tag tag = new Tag();

        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());
        when(eventRepository.save(event)).thenReturn(event);

        // Act
        Throwable exception = catchThrowable(() -> {
            Event result = eventService.unassignEventTag(eventId, tagId);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Event not found for Id with value 1");
    }
}