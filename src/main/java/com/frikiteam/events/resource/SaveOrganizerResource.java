package com.frikiteam.events.resource;

import com.frikiteam.events.domain.model.User;
import lombok.Data;

@Data
public class SaveOrganizerResource extends User {
    private String description;
    private boolean verified;
    private String logo;
}
