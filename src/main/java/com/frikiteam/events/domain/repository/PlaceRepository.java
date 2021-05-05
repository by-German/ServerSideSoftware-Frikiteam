package com.frikiteam.events.domain.repository;


import com.frikiteam.events.domain.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place,Long> {
    /*public Optional<Place> findById(Long placeId);
    public Optional<Place> findByName(String placeId);
    public Optional<Place> findByIdAndDistrictId(Long placeId, Long districtId);

    Page<Place> findAllByDistrictId(Long districtId, Pageable pageable);*/
}
