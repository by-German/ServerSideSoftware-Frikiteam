package com.frikiteam.events.domain.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.DumperOptions;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="countries")
public class Country {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    // relation inverse
    @OneToMany(mappedBy = "country", cascade = CascadeType.REMOVE)
    private List<City> cities = new ArrayList<>();

    public Country(@NotNull String name) {
        this.name = name;
    }
}
