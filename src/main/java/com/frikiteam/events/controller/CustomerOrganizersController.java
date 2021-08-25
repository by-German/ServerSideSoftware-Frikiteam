package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.model.Organizer;
import com.frikiteam.events.domain.service.CustomerService;
import com.frikiteam.events.domain.service.EventService;
import com.frikiteam.events.domain.service.OrganizerService;
import com.frikiteam.events.resource.EventResource;
import com.frikiteam.events.resource.OrganizerResource;
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
@RequestMapping("/api/customers/{customerId}/organizers")
public class CustomerOrganizersController {
    @Autowired
    private OrganizerService organizerService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    @Operation(summary = "Get events Organizers followed by a Customer", tags = {"follow-organizers"})
    public Page<OrganizerResource> getAllOrganizersByCustomerId(@PathVariable Long customerId, Pageable pageable) {
        Page<Organizer> organizersPage = organizerService.getAllByCustomerId(customerId, pageable);
        List<OrganizerResource> resources = organizersPage.getContent()
                .stream()
                .map(organizer -> mapper.map(organizer, OrganizerResource.class))
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("{organizerId}")
    @Operation(summary = "Assign the customer an organizer to follow", tags = {"follow-organizers"})
    public OrganizerResource assignCustomerEvent(@PathVariable Long customerId, @PathVariable Long organizerId) {
        return mapper.map(organizerService.assignCustomer(customerId, organizerId), OrganizerResource.class);
    }

    @DeleteMapping("{organizerId}")
    @Operation(summary = "unassigned the customer an organizer to follow", tags = {"follow-organizers"})
    public OrganizerResource unnassignCustomerEvent(@PathVariable Long customerId, @PathVariable Long organizerId) {
        return mapper.map(organizerService.unnassignCustomer(customerId, organizerId), OrganizerResource.class);
    }
}
