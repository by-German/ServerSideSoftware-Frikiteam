package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ICityService {
    public City getCityById(Long cityId);

    public City createCity(City city);
    public City updateCity(Long cityId, City requestCity);
    public ResponseEntity<?> deleteCity (Long cityId);

    public Page<City> getAllCities(Pageable pageable);
    //public Page<City> getAllCitiesByCountryId(Long countryId, Pageable pageable);
}
