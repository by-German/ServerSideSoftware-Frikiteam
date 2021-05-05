package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.EventInformation;
import com.frikiteam.events.domain.service.EventInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class EventsInformationController {

    @Autowired
    private EventInformationService eventsInformationService;

    @PostMapping("/event-information")
    public EventInformation saveEvent(@RequestBody EventInformation eventInformation){ //Convierte el objeto de JS a JSON
        return this.eventsInformationService.saveEventInformation(eventInformation);
    }

    @GetMapping("/event-information/{id}")
    public Optional<EventInformation> getByID(@PathVariable("id") Long id){
        return this.eventsInformationService.getEventInformationById(id);
    }

    @DeleteMapping("/event-information/{id}")
    public String deleteByID(@PathVariable("id") Long id){
        boolean ok = this.eventsInformationService.deleteEventInformation(id);

        if(ok){
            return "The event was eliminated with id " + id;
        }else{
            return "The event was not eliminated with id  " + id;
        }
    }

    @GetMapping("/event-information")
    public ArrayList<EventInformation> getAllEventInformation(){
        return eventsInformationService.getAllEventInformation();
    }

    @PutMapping("/event-information/{id}")
    public EventInformation updateEventInformation(@PathVariable Long id, @RequestBody EventInformation eventInformation){
        return eventsInformationService.updateEventInformation(id, eventInformation);
    }
}
