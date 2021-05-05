package com.frikiteam.events.domain.repository;


import com.frikiteam.events.domain.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    /*public Optional<City> findById(Long cityId);
    public Optional<City> findByName(String cityName);
    public Optional<City> findByIdAndCountryId(Long cityId, Long countryId);
    public Page<City> findAllByCountryId(Long countryId, Pageable pageable);*/
}
