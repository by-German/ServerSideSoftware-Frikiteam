package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.EventInformation;
import com.frikiteam.events.domain.model.Organizer;
import com.frikiteam.events.domain.repositories.EventInformationRepository;
import com.frikiteam.events.domain.service.EventInformationService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EventInformationServiceInpl implements EventInformationService {
    @Autowired
    EventInformationRepository eventsInformationRepository;
    @Override
    public ArrayList<EventInformation> getAllEventInformation(){
        return (ArrayList<EventInformation>) eventsInformationRepository.findAll();
    }

    @Override
    public EventInformation saveEventInformation(EventInformation eventInformation) {
        return eventsInformationRepository.save(eventInformation);
    }

    @Override
    public EventInformation getEventInformationById(Long id) {
        return eventsInformationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("EventInformation", "id", id));
    }

    @Override
    public ResponseEntity<?> deleteEventInformation(Long id) {

        EventInformation eventInformation = eventsInformationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EventInformation", "id", id));
        eventsInformationRepository.delete(eventInformation);
        return ResponseEntity.ok().build();
    }

    @Override
    public EventInformation updateEventInformation(Long id, EventInformation eventInformation) {
        EventInformation existed = eventsInformationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EventInformation", "id", id));

            existed.setId(eventInformation.getId());
            existed.setImage(eventInformation.getImage());
            existed.setLink(eventInformation.getLink());
            existed.setDescription(eventInformation.getDescription());

        return eventsInformationRepository.save(existed);
    }
}
