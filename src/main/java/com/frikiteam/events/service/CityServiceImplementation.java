package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.City;
import com.frikiteam.events.domain.model.Country;
import com.frikiteam.events.domain.repositories.CityRepository;
import com.frikiteam.events.domain.repositories.CountryRepository;
import com.frikiteam.events.domain.service.ICityService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImplementation implements ICityService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CountryRepository countryRepository;


    @Override
    public City getCityById(Long cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(()->new ResourceNotFoundException("City", "id", cityId));
    }

    @Override
    public City createCity(Long countryId, City city) {
        return countryRepository.findById(countryId)
                .map(country -> {
                    city.setCountry(country);
                    return cityRepository.save(city);
                }).orElseThrow(() -> new ResourceNotFoundException("Country", "Id", countryId));
    }

    @Override
    public City updateCity(Long cityId, City requestCity) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(()->new ResourceNotFoundException("City", "id", cityId));

        city.setName(requestCity.getName());
        return cityRepository.save(city);
    }

    @Override
    public ResponseEntity<?> deleteCity(Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(()->new ResourceNotFoundException("City", "id", cityId));
        cityRepository.delete(city);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<City> getAllCities(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    @Override
    public List<City> getAllCitiesByCountryId(Long countryId) {
        return countryRepository.findById(countryId)
                .map(Country::getCities)
                .orElseThrow(()->new ResourceNotFoundException("City", "id", countryId));
    }
}
