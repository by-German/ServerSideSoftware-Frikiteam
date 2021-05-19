package com.frikiteam.events.domain.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String comment;

    // relationship
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Customer customer;

    @ManyToMany(mappedBy = "comments")
    private List<Event> events = new ArrayList<>();
}
