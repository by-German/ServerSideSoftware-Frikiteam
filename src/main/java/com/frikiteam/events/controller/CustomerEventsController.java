package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.service.CustomerService;
import com.frikiteam.events.domain.service.EventService;
import com.frikiteam.events.resource.EventResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers/{customerId}/events")
public class CustomerEventsController {
    @Autowired
    private EventService eventService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    @Operation(summary = "Get events followed by a Customer", tags = {"follow-events"})
    public Page<EventResource> getAllEventsByCustomerId(@PathVariable Long customerId, Pageable pageable) {
        Page<Event> eventsPage = eventService.getAllEventsByCustomerId(customerId, pageable);
        List<EventResource> resources = eventsPage.getContent()
                .stream()
                .map(event -> mapper.map(event, EventResource.class))
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("{eventId}")
    @Operation(summary = "Assign the customer an event to follow", tags = {"follow-events"})
    public EventResource assignCustomerEvent(@PathVariable Long customerId, @PathVariable Long eventId) {
        return mapper.map(eventService.assignCustomerEvent(customerId, eventId), EventResource.class);
    }

    @DeleteMapping("{eventId}")
    @Operation(summary = "unassigned the customer an event to follow", tags = {"follow-events"})
    public EventResource unnassignCustomerEvent(@PathVariable Long customerId, @PathVariable Long eventId) {
        return mapper.map(eventService.unnassignCustomerEvent(customerId, eventId), EventResource.class);
    }

}
