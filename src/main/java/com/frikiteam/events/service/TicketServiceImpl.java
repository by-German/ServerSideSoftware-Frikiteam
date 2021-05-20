package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Customer;
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
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", id));
    }

    /*@Override
    public Ticket saveTicket(Long OfferId, Long customerId, Ticket ticket) {
        return customerRepository.findById(customerId).map(
                customer -> {
                    ticket.setCustomer(customer);
                    Offer offer = new Offer();
                    offer.setId(OfferId);
                    ticket.setOffer(offer);
                    return ticketRepository.save(ticket);
                }
        )
                .orElseThrow(()->new ResourceNotFoundException(
                        "Customer", "Id", customerId
                ));
    }*/

    @Override
    public Ticket saveTicket(Ticket ticket) {
        Offer offer = new Offer();
        offer.setId(1L);
        Customer customer = new Customer();
        customer.setId(1L);
        ticket.setCustomer(customer);
        ticket.setOffer(offer);
        return ticketRepository.save(ticket); }

    @Override
    public ResponseEntity<?> deleteTicket(Long id) {
        Ticket existed = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", id));
        ticketRepository.delete(existed);
        return ResponseEntity.ok().build();
    }

}
