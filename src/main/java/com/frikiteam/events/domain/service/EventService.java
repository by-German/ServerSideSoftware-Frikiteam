package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService{
    Event createEvent(Long organizerId, Event event);

    Event updateEvent(Long organizerId, Long eventId, Event event);

    Event sellTicket(Long eventId, int quantity);

    Event assignEventTag(Long eventId, Long tagId);

    Page<Event> getAllEventsByTagId(Long tagId, Pageable pageable);

    Page<Event> getAllEventsByOrganizerId(Long organizerId, Pageable pageable);

    Event unassignEventTag(Long eventId, Long tagId);

    List<Event> getEventByName(String name);

    List<Event> getAllEvents();

    Event getEventById(Long id);

    Page<Event> getAllEventsByCustomerId(Long customerId, Pageable pageable);

    Event assignCustomerEvent(Long customerId, Long eventId);

    Event unnassignCustomerEvent(Long customerId, Long eventId);

    void deleteEvent(Long EventId);
}
