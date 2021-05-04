package com.frikiteam.events.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true) //
@Data
@Entity
@Table(name = "customers")
public class Customer extends User {
    @Column
    private String direction;

    public Customer(){}

    public Customer(String firstName, String lastName, String password, String email, String direction) {
        super(firstName, lastName, password, email);
        this.direction = direction;
    }
}
