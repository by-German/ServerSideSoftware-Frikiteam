package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Offer;
import com.frikiteam.events.domain.model.Ticket;
import com.frikiteam.events.domain.service.TicketService;
import com.frikiteam.events.resource.OfferResource;
import com.frikiteam.events.resource.SaveOfferResource;
import com.frikiteam.events.resource.SaveTicketResource;
import com.frikiteam.events.resource.TicketResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TicketsController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get Tickets", description = "Get All Tickets by Pages", tags = {"tickets"})
    @ApiResponse(
            responseCode = "200",
            description = "All Tickets returned",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/tickets")
    public Page<TicketResource> getAllTickets(Pageable pageable){
        Page<Ticket> tickets = ticketService.getAllTickets(pageable);
        List<TicketResource> resources = tickets.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    /*@PostMapping("/tickets")
    public TicketResource createTicket(@PathVariable Long OfferId,@PathVariable Long id, @RequestBody SaveTicketResource ticketResource){
        Ticket ticket = convertToEntity(ticketResource);
        return convertToResource(ticketService.saveTicket(OfferId, id, ticket));
    }*/

    @Operation(summary = "Save a Ticket   ", description = "Save a Ticket", tags = {"tickets"})
    @ApiResponse(
            responseCode = "200",
            description = "Return the ticket saved",
            content = @Content(mediaType = "application/json")
    )
    @PostMapping("/tickets")
    public TicketResource saveTicket(@Valid @RequestBody SaveTicketResource resource) {
        Ticket ticket= convertToEntity(resource);
        return convertToResource(ticketService.saveTicket(ticket));
    }



    @Operation(summary = "Get Ticket by id", description = "Get a Ticket given an id", tags = {"tickets"})
    @ApiResponse(
            responseCode = "200",
            description = "Return a ticket",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/tickets/{id}")
    public TicketResource getByIdTicket(@PathVariable Long id){
        return convertToResource(ticketService.getTicketById(id));
    }

    @Operation(summary = "Delete a Ticket", description = "remove a Ticket given an id", tags = {"tickets"})
    @ApiResponse(
            responseCode = "200",
            description = "Return ResponseEntity",
            content = @Content(mediaType = "application/json")
    )
    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<?> deleteOffer(@PathVariable Long id){return ticketService.deleteTicket(id); }

    private Ticket convertToEntity(SaveTicketResource resource) {return mapper.map(resource, Ticket.class);
    }
    private TicketResource convertToResource(Ticket entity) {
        return mapper.map(entity, TicketResource.class);
    }
}
