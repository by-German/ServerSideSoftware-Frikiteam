package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService{
    Event createEvent(Long organizerId, Long placeId, Event event);

    Event updateEvent(Long organizerId, Long eventId, Event event);

    Event assignEventTag(Long eventId, Long tagId);

    Page<Event> getAllEventsByTagId(Long tagId, Pageable pageable);

    Event unassignEventTag(Long eventId, Long tagId);

    Event getEventByName(String name);
}
