package com.frikiteam.events.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.GregorianCalendar;

@EqualsAndHashCode(callSuper = true) //
@Data
@Entity
@Table(name = "customers")
public class Customer extends User {
    @Column(name = "date_birth")
    private GregorianCalendar dateBirth;

    public Customer(){}

    public Customer(String firstName, String lastName, String password, String email, GregorianCalendar dateBirth) {
        super(firstName, lastName, password, email);
        this.dateBirth = dateBirth;
    }
}
