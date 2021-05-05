package com.frikiteam.events.domain.repository;


import com.frikiteam.events.domain.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Long> {
    /*public Optional<District> findById(Long districtId);
    public Optional<District> findByName(Long districtName);
    public Optional<District> findByIdAndCityId(Long districtId, Long cityId);

    Page<District> findAllByCityId(Long cityId, Pageable pageable);*/
}
