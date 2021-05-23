package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.EventType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface EventTypeService {
    Page<EventType> getAllEventTypes(Pageable pageable);
    EventType getEventTypeById(Long eventTypeId);
    EventType createEventType(EventType eventType);
    EventType updateEventType(Long eventTypeId, EventType eventTypeDetails);
    ResponseEntity<?> deleteEventType(Long eventTypeId);

    EventType assignTypeEventToEvent(Long typeId, Long eventId);
}
