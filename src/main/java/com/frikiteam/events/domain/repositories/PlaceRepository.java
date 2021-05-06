package com.frikiteam.events.domain.repositories;


import com.frikiteam.events.domain.model.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place,Long> {
    /*public Optional<Place> findById(Long placeId);
    public Optional<Place> findByName(String placeId);
    public Optional<Place> findByIdAndDistrictId(Long placeId, Long districtId);

    Page<Place> findAllByDistrictId(Long districtId, Pageable pageable);*/
}
