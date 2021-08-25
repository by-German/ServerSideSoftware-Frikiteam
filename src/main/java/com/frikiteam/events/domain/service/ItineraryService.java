package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.Itinerary;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ItineraryService {
    Itinerary createItinerary(Long eventId, Itinerary itinerary);
    Itinerary update(Long id, Itinerary itinerary);
    ResponseEntity<?> delete(Long id);
    List<Itinerary> getAllByEventId(Long eventId);
}
