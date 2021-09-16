package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICityService {
    City getCityById(Long cityId);

    City createCity(Long countryId, City city);
    City updateCity(Long cityId, City requestCity);
    ResponseEntity<?> deleteCity (Long cityId);

    Page<City> getAllCities(Pageable pageable);
    List<City> getAllCitiesByCountryId(Long countryId);
}
