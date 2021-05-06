package com.frikiteam.events.domain.repositories;


import com.frikiteam.events.domain.model.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, Long> {
    /*public Optional<District> findById(Long districtId);
    public Optional<District> findByName(Long districtName);
    public Optional<District> findByIdAndCityId(Long districtId, Long cityId);

    Page<District> findAllByCityId(Long cityId, Pageable pageable);*/
}
