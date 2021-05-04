package com.frikiteam.events.resource;

import com.frikiteam.events.domain.model.User;
import lombok.Data;

@Data // between generate getters and setter for class super
public class CustomerResource extends User {
    private String direction;
}
