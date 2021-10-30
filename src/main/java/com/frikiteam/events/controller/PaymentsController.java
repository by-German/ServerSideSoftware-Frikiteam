package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.model.Ticket;
import com.frikiteam.events.domain.service.TicketService;
import com.frikiteam.events.resource.EventResource;
import com.frikiteam.events.resource.PaymentIntentDto;
import com.frikiteam.events.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/payments")
public class PaymentsController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<String> createPayment(@RequestBody PaymentIntentDto paymentIntentDto) throws StripeException {
        PaymentIntent paymentIntent = paymentService.createPaymentIntent(paymentIntentDto);
        String response = paymentIntent.toJson();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<String> confirmPayment(@PathVariable String id) throws StripeException {
        PaymentIntent paymentIntent = paymentService.confirmPaymentIntent(id);
        String response = paymentIntent.toJson();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> cancelPayment(@PathVariable String id) throws StripeException {
        PaymentIntent paymentIntent = paymentService.cancelPaymentIntent(id);
        String response = paymentIntent.toJson();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("customers/{id}")
    public List<EventResource> getAllTicketsByCustomerId(@PathVariable Long id) {
        return ticketService.getAllBoughtByCustomerId(id).stream()
                .map(event -> mapper.map(event, EventResource.class))
                .collect(Collectors.toList());
    }
}
