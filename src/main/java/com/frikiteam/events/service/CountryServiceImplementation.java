package com.frikiteam.events.service;


import com.frikiteam.events.domain.model.Country;
import com.frikiteam.events.domain.repositories.CountryRepository;
import com.frikiteam.events.domain.service.ICountryService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImplementation implements ICountryService {
    @Autowired
    private CountryRepository countryRepository;


    @Override
    public Country getCountryById(Long countryId) {
        return countryRepository.findById(countryId)
                .orElseThrow(()->new ResourceNotFoundException("Country", "id", countryId));
    }

    @Override
    public Country createCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country updateCountry(Long countryId, Country requestCountry) {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(()->new ResourceNotFoundException("Country", "id", countryId));
        country.setName(requestCountry.getName());
        return countryRepository.save(country);
    }

    @Override
    public ResponseEntity<?> deleteCountry(Long countryId) {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "id", countryId));
        countryRepository.delete(country);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<Country> getAllCountries(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }
}
