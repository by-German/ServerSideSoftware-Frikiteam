package com.frikiteam.events.domain.model;

import javax.persistence.*;

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


}
