package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IPlaceService {
    public Place getPlaceById(Long placeId);

    public Place createPlace(Long districtId, Place place);
    public Place updatePlace(Long placeId, Place requestPlace);
    public ResponseEntity<?> deletePlace (Long placeId);

    public Page<Place> getAllPlaces(Pageable pageable);
    //public Page<Place> getAllPlacesByDistrictId(Long districtId, Pageable pageable);
}
