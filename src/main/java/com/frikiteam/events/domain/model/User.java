package com.frikiteam.events.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "firsts_name")
    protected String firstName;

    @Column(name = "last_name")
    protected String lastName;

    @Column(unique = true)
    protected String email;

    protected String password;

    private String logo;

    public User() {}

    public User(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }
}
