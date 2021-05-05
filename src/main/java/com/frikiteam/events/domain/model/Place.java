package com.frikiteam.events.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String reference;

    /*@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "district_id", nullable = false)
    @JsonIgnore
    private District district;*/

    public Place() {
    }

    public Place(Long id, @NotNull String name, @NotNull String reference, District district) {
        this.id = id;
        this.name = name;
        this.reference = reference;
        //this.district = district;
    }


    public Long getId() {
        return id;
    }

    public Place setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Place setName(String name) {
        this.name = name;
        return this;
    }

    public String getReference() {
        return reference;
    }

    public Place setReference(String reference) {
        this.reference = reference;
        return this;
    }

    /*public District getDistrict() {
        return district;
    }

    public Place setDistrict(District district) {
        this.district = district;
        return this;
    }*/
}
