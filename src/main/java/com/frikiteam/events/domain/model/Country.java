package com.frikiteam.events.domain.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    public Country(Long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    public Country() {

    }

    public Long getId() {
        return id;
    }

    public Country setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Country setName(String name) {
        this.name = name;
        return this;
    }
}
