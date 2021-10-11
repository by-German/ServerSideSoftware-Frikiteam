package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.service.EventService;
import com.frikiteam.events.resource.EventResource;
import com.frikiteam.events.resource.SaveEventResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("api/organizers/{organizerId}/events")
public class OrganizerEventsController {
    @Autowired
    private EventService eventService;
    @Autowired
    private ModelMapper mapper;


    @Operation(summary = "Create Event", description = "creation of an event by an organizer, at a place", tags = {"organizers-events"})
    @ApiResponse(
            responseCode = "200",
            description = "Event returned",
            content = @Content(mediaType = "application/json")
    )
    @PostMapping
    public EventResource createEvent
            (@PathVariable Long organizerId, @RequestBody SaveEventResource resource) {

        // creation event for an organizer, at a place
        Event event = mapper.map(resource, Event.class);
        event.setId(null);
        return mapper.map(eventService.createEvent(organizerId, event), EventResource.class);
    }

    @PutMapping("/{eventId}")
    @Operation(summary = "Update Event", description = "update data of event", tags = {"organizers-events"})
    public EventResource updateEvent
            (@PathVariable Long organizerId, @PathVariable Long eventId, @RequestBody SaveEventResource resource ) {

        Event event = mapper.map(resource, Event.class);
        return mapper.map(eventService.updateEvent(organizerId, eventId, event), EventResource.class);
    }

    @GetMapping
    @Operation(summary = "Get all events of an Organizer", tags = {"organizers-events"})
    public Page<EventResource> getAllEventsByOrganizerId(@PathVariable Long organizerId, Pageable pageable) {
        Page<Event> eventsPage = eventService.getAllEventsByOrganizerId(organizerId, pageable);
        List<EventResource> resources = eventsPage.getContent()
                .stream()
                .map(event -> mapper.map(event, EventResource.class))
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());

    }

    @Operation(summary = "Delete Event", description = "delete an event by organizer id and event id", tags = {"organizers-events"})
    @ApiResponse(
            responseCode = "200",
            description = "Response status",
            content = @Content(mediaType = "application/json")
    )
    @DeleteMapping("/{eventId}")
    public void deleteEvent(@PathVariable Long organizerId, @PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
    }
}
