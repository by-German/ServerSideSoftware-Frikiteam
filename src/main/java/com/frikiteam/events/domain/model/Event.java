package com.frikiteam.events.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "events")
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logo;
    private String information;
    private String name;
    private Double price;
    private int quantity;
    private int sold;
    private Boolean verified;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;

    // relationships
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Organizer organizer;

    @ManyToOne(fetch = FetchType.LAZY)
    private EventType eventType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Place place;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventInformation> eventInformations = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "events_tags",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<Tag> tags = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "social_networks_Events",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "social_network_id")})
    private List<SocialNetwork> socialNetworks = new ArrayList<>();

    // inverse relationships
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    List<EventQualification> eventQualifications = new ArrayList<>();

    @ManyToMany(mappedBy = "events")
    private List<Customer> customers = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    List<Itinerary> itineraries = new ArrayList<>();


    public Event(String name, int quantity, int sold,
                 Double price, String information, Boolean verified,
                 GregorianCalendar startDate, GregorianCalendar endDate) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.information = information;
        this.verified = verified;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sold = sold;
    }
}