package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.model.EventInformation;
import com.frikiteam.events.domain.model.Itinerary;
import com.frikiteam.events.domain.model.User;
import com.frikiteam.events.domain.repositories.EventRepository;
import com.frikiteam.events.domain.repositories.ItineraryRepository;
import com.frikiteam.events.domain.service.ItineraryService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItineraryServiceImpl implements ItineraryService {
    @Autowired
    private ItineraryRepository itineraryRepository;
    @Autowired
    private EventRepository eventRepository;

    @Override
    public Itinerary createItinerary(Long eventId, Itinerary itinerary) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));

        itinerary.setEvent(event);
        return itineraryRepository.save(itinerary);
    }

    @Override
    public Itinerary update(Long id, Itinerary itinerary) {
        return itineraryRepository.findById(id)
                .map(existed -> {
                    existed.setName(itinerary.getName());
                    existed.setStartDateTime(itinerary.getStartDateTime());
                    existed.setEndDateTime(itinerary.getEndDateTime());
                    return itineraryRepository.save(existed);
                }).orElseThrow(() -> new ResourceNotFoundException("Itinerary", "id", id));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Itinerary itinerary =  itineraryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Itinerary", "id", id));
        itineraryRepository.delete(itinerary);
        return ResponseEntity.ok().build();
    }

    @Override
    public List<Itinerary> getAllByEventId(Long eventId) {
        return eventRepository.findById(eventId)
                .map(Event::getItineraries)
                .orElseThrow(() -> new ResourceNotFoundException("Itinerary", "id", eventId));
    }
}
