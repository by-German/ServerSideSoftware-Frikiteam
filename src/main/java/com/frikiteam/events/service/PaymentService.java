package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Ticket;
import com.frikiteam.events.domain.service.CustomerService;
import com.frikiteam.events.domain.service.EventService;
import com.frikiteam.events.domain.service.TicketService;
import com.frikiteam.events.resource.PaymentIntentDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EventService eventService;

    @Value("${stripe.key.secret}")
    String secretKey;

    public PaymentIntent createPaymentIntent(PaymentIntentDto paymentIntentDto) throws StripeException {
        Stripe.apiKey = secretKey;
        Map<String, Object> params = new HashMap<>();

        params.put("amount", paymentIntentDto.getAmount());
        params.put("currency", paymentIntentDto.getCurrency());
        params.put("description", paymentIntentDto.getDescription() + " - " + paymentIntentDto.getEmail());
        params.put("receipt_email", paymentIntentDto.getEmail());

        List<Object> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");
        params.put("payment_method_types", paymentMethodTypes);
        createTicket(paymentIntentDto);

        return PaymentIntent.create(params);
    }

    public PaymentIntent confirmPaymentIntent(String paymentId) throws StripeException {
        Stripe.apiKey = secretKey;

        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentId);
        Map<String, Object> params = new HashMap<>();
        params.put("payment_method", "pm_card_visa");

        return paymentIntent.confirm(params);
    }

    // cancel payment intent
    public PaymentIntent cancelPaymentIntent(String paymentId) throws StripeException {
        Stripe.apiKey = secretKey;

        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentId);

        return paymentIntent.cancel();
    }

    public void createTicket(PaymentIntentDto payment) {
        Ticket ticket = new Ticket();
        ticket.setAmountPrice((double)payment.getAmount() / 100);
        if (payment.getCustomerId() == 0) ticket.setCustomer(null);
        else ticket.setCustomer(customerService.getCustomerById(payment.getCustomerId()));
        ticket.setEvent(eventService.getEventById(payment.getEventId()));
        eventService.sellTicket(payment.getEventId(), 1);

        ticketService.saveTicket(ticket);
    }
}
