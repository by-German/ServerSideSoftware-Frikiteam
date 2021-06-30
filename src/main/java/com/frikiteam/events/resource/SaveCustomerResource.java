package com.frikiteam.events.resource;

import com.frikiteam.events.domain.model.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.GregorianCalendar;

// min necessary for create a Customer
@Data
public class SaveCustomerResource extends User{
    private GregorianCalendar dateBirth;
}