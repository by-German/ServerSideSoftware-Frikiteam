package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Place;
import com.frikiteam.events.domain.service.IPlaceService;
import com.frikiteam.events.resource.PlaceResource;
import com.frikiteam.events.resource.SavePlaceResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/districts/{districtId}/places")
public class DistrictPlacesController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private IPlaceService placeService;

    private Place convertToEntity(SavePlaceResource resource){
        return mapper.map(resource,Place.class);
    }
    private PlaceResource convertToResource(Place entity){
        return mapper.map(entity,PlaceResource.class);
    }

    @GetMapping
    @Operation(summary = "Get all places", tags = {"district-places"})
    public Page<PlaceResource> getAllPlaces(Pageable pageable){
        List<PlaceResource> resources = placeService.getAllPlaces(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @PostMapping
    @Operation(summary = "create a place by a district id", tags = {"district-places"})
    public PlaceResource createPlace(@PathVariable Long districtId, @Valid @RequestBody SavePlaceResource resource){
        Place place = convertToEntity(resource);
        return convertToResource(placeService.createPlace(districtId, place));
    }

    @PutMapping("{placeId}")
    @Operation(summary = "update a place", tags = {"district-places"})
    public PlaceResource updatePlace(@PathVariable Long placeId,@RequestBody SavePlaceResource resource){
        Place place = convertToEntity(resource);
        return convertToResource(placeService.updatePlace(placeId,place));
    }

    @DeleteMapping("{placeId}")
    @Operation(summary = "Delete a place", tags = {"district-places"})
    public ResponseEntity<?> deletePlace(@PathVariable Long placeId)
    {
        return placeService.deletePlace(placeId);
    }

    @GetMapping("{placeId}")
    @Operation(summary = "Get a place by id", tags = {"district-places"})
    public PlaceResource getPlaceById(@PathVariable Long placeId){
        return convertToResource(placeService.getPlaceById(placeId));
    }
}
