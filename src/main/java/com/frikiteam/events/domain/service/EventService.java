package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.Event;

public interface EventService{
    Event createEvent(Long organizerId, Long placeId, Event event);

    Event updateEvent(Long organizerId, Long eventId, Event event);
}
