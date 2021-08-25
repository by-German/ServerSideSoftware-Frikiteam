package com.frikiteam.events.controller;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.frikiteam.events.domain.model.EventInformation;
import com.frikiteam.events.domain.model.Itinerary;
import com.frikiteam.events.domain.service.ItineraryService;
import com.frikiteam.events.resource.EventInformationResource;
import com.frikiteam.events.resource.ItineraryResource;
import com.frikiteam.events.resource.SaveEventInformationResource;
import com.frikiteam.events.resource.SaveItineraryResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/events/{eventId}/itineraries")
public class EventItinerariesController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ItineraryService itineraryService;

    @GetMapping
    @Operation(summary = "get all itineraries of an event by event id", tags = {"event-itineraries"})
    public List<ItineraryResource> getItinerariesByEventId(@PathVariable Long eventId) {
        List<Itinerary> itineraries =itineraryService.getAllByEventId(eventId);
        return itineraries.stream()
                .map(eventInformation -> mapper.map(eventInformation, ItineraryResource.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    @Operation(summary = "create itinerary for an event", tags = {"event-itineraries"})
    public ItineraryResource createItinerary(@PathVariable Long eventId, @RequestBody SaveItineraryResource resource){
        Itinerary itinerary = mapper.map(resource, Itinerary.class);
        return mapper.map(itineraryService.createItinerary(eventId, itinerary), ItineraryResource.class);
    }

    @PutMapping("{itineraryId}")
    @Operation(summary = "update itinerary for an event", tags = {"event-itineraries"})
    public ItineraryResource updateItinerary(@PathVariable Long itineraryId, @RequestBody SaveItineraryResource resource){
        Itinerary itinerary = mapper.map(resource, Itinerary.class);
        return mapper.map(itineraryService.update(itineraryId, itinerary), ItineraryResource.class);
    }

    @DeleteMapping("{itineraryId}")
    @Operation(summary = "update itinerary for an event", tags = {"event-itineraries"})
    public ResponseEntity<?> deleteItinerary(@PathVariable Long itineraryId){
        return itineraryService.delete(itineraryId);
    }
}
