package com.rodrigo.miparte.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "districts")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String reference;

    /*@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;*/


    public District() {
    }

    public District(Long id, @NotNull String name, @NotNull String reference, City city) {
        this.id = id;
        this.name = name;
        this.reference = reference;
        //this.city = city;
    }

    public Long getId() {
        return id;
    }

    public District setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public District setName(String name) {
        this.name = name;
        return this;
    }

    public String getReference() {
        return reference;
    }

    public District setReference(String reference) {
        this.reference = reference;
        return this;
    }

    /*public City getCity() {
        return city;
    }

    public District setCity(City city) {
        this.city = city;
        return this;
    }*/
}
