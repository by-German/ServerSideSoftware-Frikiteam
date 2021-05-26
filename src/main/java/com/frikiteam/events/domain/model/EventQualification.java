package com.frikiteam.events.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "event_qualifications")
public class EventQualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_quantity")
    private int startQuantity;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Ticket ticket;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStartQuantity() {
        return startQuantity;
    }

    public void setStartQuantity(int startQuantity) {
        this.startQuantity = startQuantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public EventQualification(){}
}
