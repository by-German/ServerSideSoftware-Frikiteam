package com.frikiteam.events.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "social_networks")
public class SocialNetwork {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // relationship
    @ManyToMany(mappedBy = "socialNetworks")
    private List<Event> events = new ArrayList<>();

    public SocialNetwork(String name) {
        this.name = name;
    }
}
