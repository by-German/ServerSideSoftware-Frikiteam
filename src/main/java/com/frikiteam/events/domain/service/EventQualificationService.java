package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.EventQualification;
import org.springframework.http.ResponseEntity;

public interface EventQualificationService {
    public EventQualification saveEventInformation(EventQualification eventQualification);

    public EventQualification getEventInformationById(Long id);

    public ResponseEntity<?> deleteEventInformation(Long id);
}
