package com.frikiteam.events.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Data
@Entity
@Table(name = "organizers")
public class Organizer extends User{
    @Lob
    private String description;

    private Boolean verified;

    // inverse relationship
    @OneToMany(mappedBy = "organizer")
    private List<Event> events = new ArrayList<>();

    // inverse relationships
    @ManyToMany(mappedBy = "organizers")
    private List<Customer> customers = new ArrayList<>();


    public Organizer() { }

    public Organizer(String firstName, String lastName, String password, String email, String description, boolean verified) {
        super(firstName, lastName, password, email);
        this.description = description;
        this.verified = verified;
    }
}
