package com.frikiteam.events.domain.repositories;

import com.frikiteam.events.domain.model.Country;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    /*public Optional<Country> findById(Long countryId);
    public Optional<Country> findByName(String countryName);
    public void deleteById(Long countryId);*/
}
