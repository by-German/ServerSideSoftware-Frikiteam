package com.frikiteam.events.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@EqualsAndHashCode(callSuper = true) //
@Data
@Entity
@Table(name = "customers")
public class Customer extends User {
    @Column(name = "date_birth")
    private GregorianCalendar dateBirth;

    // relationships
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // methods for modify this table, be will executing in this class
    @JoinTable(name = "follow_events",
            joinColumns = {@JoinColumn(name = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")})
    private List<Event> events = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // methods for modify this table, be will executing in this class
    @JoinTable(name = "follow_organizers",
            joinColumns = {@JoinColumn(name = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "organizer_id")})
    private List<Organizer> organizers = new ArrayList<>();

    @OneToMany(mappedBy = "customer") // inverse
    private List<Ticket> tickets = new ArrayList<>();

    public Customer(){}
    public Customer(String firstName, String lastName, String password, String email, GregorianCalendar dateBirth) {
        super(firstName, lastName, password, email);
        this.dateBirth = dateBirth;
    }
}
