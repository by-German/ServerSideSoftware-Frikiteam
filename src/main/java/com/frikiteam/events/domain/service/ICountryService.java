package com.frikiteam.events.domain.service;


import com.frikiteam.events.domain.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ICountryService {
    public Country getCountryById(Long countryId);

    public Country createCountry(Country country);
    public Country updateCountry(Long countryId, Country requestCountry);
    ResponseEntity<?> deleteCountry(Long countryId);

    public Page<Country> getAllCountries(Pageable pageable);
}
