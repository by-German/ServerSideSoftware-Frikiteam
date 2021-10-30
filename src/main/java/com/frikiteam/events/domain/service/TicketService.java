package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.model.Offer;
import com.frikiteam.events.domain.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketService {
    Page<Ticket> getAllTickets(Pageable pageable);
    List<Event> getAllBoughtByCustomerId(Long customerId);
    Ticket getTicketById(Long id);
    Ticket saveTicket(Ticket ticket);
    ResponseEntity<?> deleteTicket(Long id);
}
