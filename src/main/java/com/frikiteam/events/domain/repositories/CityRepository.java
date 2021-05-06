package com.frikiteam.events.domain.repositories;


import com.frikiteam.events.domain.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    /*public Optional<City> findById(Long cityId);
    public Optional<City> findByName(String cityName);
    public Optional<City> findByIdAndCountryId(Long cityId, Long countryId);
    public Page<City> findAllByCountryId(Long countryId, Pageable pageable);*/
}
