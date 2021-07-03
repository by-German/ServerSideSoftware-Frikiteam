package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.EventQualification;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventQualificationService {
    EventQualification saveEventQualification(Long eventId, EventQualification eventQualification);

    ResponseEntity<?> deleteEventQualification(Long id);

    List<EventQualification> getEventQualificationByEventId(Long eventId);
}
