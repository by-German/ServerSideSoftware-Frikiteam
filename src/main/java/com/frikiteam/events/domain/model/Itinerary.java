package com.frikiteam.events.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "intineraries")
public class Itinerary {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;

    // relationship
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Event event;


    public Itinerary(String name, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
