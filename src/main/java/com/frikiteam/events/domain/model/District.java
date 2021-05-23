package com.frikiteam.events.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "districts")
public class District {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;

    // relationship
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private City city;

    // relation inverse
    @OneToMany(mappedBy = "district", cascade = CascadeType.REMOVE)
    private List<Place> places = new ArrayList<>();

    public District(@NotNull String name) {
        this.name = name;
    }
}
