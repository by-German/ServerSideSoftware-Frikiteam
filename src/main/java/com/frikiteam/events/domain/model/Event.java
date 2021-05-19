package com.frikiteam.events.domain.model;

import javax.persistence.*;
import javax.xml.stream.events.Comment;

@Entity
@Table(name = "events")
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String information;
    private int quantity;
    private Double price;

    // relationship
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private EventType eventType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Organizer organizer;

    @ManyToMany

}
