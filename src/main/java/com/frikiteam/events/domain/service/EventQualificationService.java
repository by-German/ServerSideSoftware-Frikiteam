package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.EventQualification;
import org.springframework.http.ResponseEntity;

public interface EventQualificationService {
    public EventQualification saveEventQualification(EventQualification eventQualification);

    public EventQualification getEventQualificationById(Long id);

    public ResponseEntity<?> deleteEventQualification(Long id);
}
