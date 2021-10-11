package com.frikiteam.events.resource;

import com.frikiteam.events.domain.model.User;
import lombok.Data;

import javax.persistence.Lob;

@Data
public class OrganizerResource extends User {
    private String description;
    private Boolean verified;
}
