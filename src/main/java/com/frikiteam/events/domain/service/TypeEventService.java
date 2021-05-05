package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.TypeEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TypeEventService {
    Page<TypeEvent> getAllTypeEvents(Pageable pageable);
    TypeEvent getTypeEventById(Long typeEventId);
    TypeEvent createTypeEvent(TypeEvent typeEvent);
    TypeEvent updateTypeEvent(Long typeEventId, TypeEvent typeEventDetails);
    ResponseEntity<?> deleteTypeEvent(Long typeEventId);
}
