package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.Offer;
import com.frikiteam.events.domain.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TicketService {
    Page<Ticket> getAllTickets(Pageable pageable);
    Ticket getTicketById(Long id);
    //Ticket saveTicket(Long OfferId, Long customerId, Ticket ticket);
    Ticket saveTicket(Ticket ticket);
    ResponseEntity<?> deleteTicket(Long id);
    //Ticket updateTicket(Long id, Ticket ticket);
}
