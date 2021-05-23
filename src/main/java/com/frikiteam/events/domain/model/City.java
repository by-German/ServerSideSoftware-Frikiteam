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
@Table(name = "cities")
public class City {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;

    // relationship
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Country country;

    //relation inverse
    @OneToMany(mappedBy = "city", cascade = CascadeType.REMOVE)
    private List<District> districts = new ArrayList<>();

    public City(@NotNull String name) {
        this.name = name;
    }
}
