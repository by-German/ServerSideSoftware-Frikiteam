package com.rodrigo.miparte.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String reference;

    /*@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    @JsonIgnore
    private Country country;*/

    public City() {
    }

    public City(Long id, @NotNull String name, @NotNull String reference, Country country) {
        this.id = id;
        this.name = name;
        this.reference = reference;
        //this.country = country;
    }


    public Long getId() {
        return id;
    }

    public City setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public City setName(String name) {
        this.name = name;
        return this;
    }

    public String getReference() {
        return reference;
    }

    public City setReference(String reference) {
        this.reference = reference;
        return this;
    }

    /*public Country getCountry() {
        return country;
    }

    public City setCountry(Country country) {
        this.country = country;
        return this;
    }*/
}
