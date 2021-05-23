package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Place;
import com.frikiteam.events.domain.repositories.DistrictRepository;
import com.frikiteam.events.domain.repositories.PlaceRepository;
import com.frikiteam.events.domain.service.IPlaceService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PlaceServiceImplementation implements IPlaceService {
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public Place getPlaceById(Long placeId) {
        return placeRepository.findById(placeId)
                .orElseThrow(()->new ResourceNotFoundException("Place", "id", placeId));
    }

    @Override
    public Place createPlace(Long districtId, Place place) {
        return districtRepository.findById(districtId)
                .map(district -> {
                    place.setDistrict(district);
                    return placeRepository.save(place);
                })
                .orElseThrow(() -> new ResourceNotFoundException("District", "Id", districtId));
    }

    @Override
    public Place updatePlace(Long placeId, Place requestPlace) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(()->new ResourceNotFoundException("Place", "id", placeId));
        place.setName(requestPlace.getName());
        place.setReference(requestPlace.getReference());
        return placeRepository.save(place);
    }

    @Override
    public ResponseEntity<?> deletePlace(Long placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(()->new ResourceNotFoundException("Place", "id", placeId));
        placeRepository.delete(place);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<Place> getAllPlaces(Pageable pageable) {
        return placeRepository.findAll(pageable);
    }
}
