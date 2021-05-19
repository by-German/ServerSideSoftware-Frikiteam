package com.frikiteam.events.domain.model;

import javax.persistence.*;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "event_comments",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "comment_id")})
    private List<Comment> comments = new ArrayList<>();
}
