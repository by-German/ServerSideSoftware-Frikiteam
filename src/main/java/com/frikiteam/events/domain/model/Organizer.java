package com.frikiteam.events.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@EqualsAndHashCode
@Data
@Entity
@Table(name = "organizers")
public class Organizer extends User{
    @Lob
    private String description;

    private boolean verified;

    private String logo;

    public Organizer(String description, boolean verified, String logo) {
        this.description = description;
        this.verified = verified;
        this.logo = logo;
    }

    public Organizer(String firstName, String lastName, String password, String email, String description, boolean verified, String logo) {
        super(firstName, lastName, password, email);
        this.description = description;
        this.verified = verified;
        this.logo = logo;
    }
}
