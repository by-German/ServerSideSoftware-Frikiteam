package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Customer;
import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.model.Offer;
import com.frikiteam.events.domain.model.Ticket;
import com.frikiteam.events.domain.repositories.CustomerRepository;
import com.frikiteam.events.domain.repositories.OfferRepository;
import com.frikiteam.events.domain.repositories.TicketRepository;
import com.frikiteam.events.domain.service.TicketService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Ticket> getAllTickets(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    @Override
    public List<Event> getAllBoughtByCustomerId(Long customerId) {
        return customerRepository.findById(customerId)
                .map(customer -> {
                    List<Event> events = new ArrayList<>();
                    for (Ticket ticket: customer.getTickets()) {
                        events.add(ticket.getEvent());
                    }
                    return events;
                })
                .orElse(new ArrayList<>());
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", id));
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public ResponseEntity<?> deleteTicket(Long id) {
        Ticket existed = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", id));
        ticketRepository.delete(existed);
        return ResponseEntity.ok().build();
    }

}
