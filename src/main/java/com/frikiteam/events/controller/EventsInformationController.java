package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.EventInformation;
import com.frikiteam.events.domain.model.Organizer;
import com.frikiteam.events.domain.service.EventInformationService;
import com.frikiteam.events.resource.OrganizerResource;
import com.frikiteam.events.resource.SaveOrganizerResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class EventsInformationController {

    @Autowired
    private EventInformationService eventsInformationService;

    @Operation(summary = "Save EventInformation", description = "Save an eventinformation", tags = {"event-information"})
    @ApiResponse(
            responseCode = "200",
            description = "Return the eventinformation saved",
            content = @Content(mediaType = "application/json")
    )
    @PostMapping("/event-information")
    public EventInformation saveEvent(@RequestBody EventInformation eventInformation){ //Convierte el objeto de JS a JSON
        return this.eventsInformationService.saveEventInformation(eventInformation);
    }

    @Operation(summary = "Get EventInformation by id", description = "Get a eventinformation given an id", tags = {"event-information"})
    @ApiResponse(
            responseCode = "200",
            description = "Return a eventinformation",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/event-information/{id}")
    public EventInformation getByID(@PathVariable("id") Long id){
        return this.eventsInformationService.getEventInformationById(id);
    }

    @Operation(summary = "Delete an EventInformation", description = "Remove an eventinformation given an id", tags = {"event-information"})
    @ApiResponse(
            responseCode = "200",
            description = "Return ResponseEntity",
            content = @Content(mediaType = "application/json")
    )
    @DeleteMapping("/event-information/{id}")
    public ResponseEntity<?>deleteByID(@PathVariable("id") Long id){ return eventsInformationService.deleteEventInformation(id);}


    @Operation(summary = "Get EventsInformation", description = "Get All eventsinformation by Pages", tags = {"event-information"})
    @ApiResponse(
            responseCode = "200",
            description = "All EventsInformation returned",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/event-information")
    public ArrayList<EventInformation> getAllEventInformation(){
        return eventsInformationService.getAllEventInformation();
    }

    @Operation(summary = "Update an EventInformation", description = "Update an eventinformation given an id and eventinformation body", tags = {"event-information"})
    @ApiResponse(
            responseCode = "200",
            description = "Return the eventinformation updated",
            content = @Content(mediaType = "application/json")
    )
    @PutMapping("/event-information/{id}")
    public EventInformation updateEventInformation(@PathVariable Long id, @RequestBody EventInformation eventInformation){
        return eventsInformationService.updateEventInformation(id, eventInformation);
    }
}
