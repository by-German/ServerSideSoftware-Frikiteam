package com.frikiteam.events.domain.repository;

import com.frikiteam.events.domain.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    /*public Optional<Country> findById(Long countryId);
    public Optional<Country> findByName(String countryName);
    public void deleteById(Long countryId);*/
}
