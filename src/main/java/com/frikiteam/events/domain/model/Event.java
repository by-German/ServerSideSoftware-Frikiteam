package com.frikiteam.events.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "events")
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int quantity;
    private Double price;
    private String information;

    // relationship
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Organizer organizer;

    @ManyToOne(fetch = FetchType.LAZY)
    private EventType eventType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    private EventInformation eventInformation;

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
}
