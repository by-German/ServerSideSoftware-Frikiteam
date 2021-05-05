package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.EventInformation;
import com.frikiteam.events.domain.repositories.EventInformationRepository;
import com.frikiteam.events.domain.service.EventInformationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<EventInformation> getEventInformationById(Long id) {
        return eventsInformationRepository.findById(id);
    }

    @Override
    public boolean deleteEventInformation(Long id) {
        try{
            eventsInformationRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    @Override
    public EventInformation updateEventInformation(Long id, EventInformation eventInformation) {
        EventInformation existed = eventsInformationRepository.findById(id).orElse(null);

        if(existed==null){
            return null;
        }


        existed.setImage(eventInformation.getImage());
        existed.setDescription(eventInformation.getDescription());
        existed.setStartDate(eventInformation.getStartDate());
        existed.setLink(eventInformation.getLink());
        existed.setEndDate(eventInformation.getEndDate());

        return eventsInformationRepository.save(existed);
    }
}
