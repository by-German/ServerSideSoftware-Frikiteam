package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.District;
import com.frikiteam.events.domain.service.IDistrictService;
import com.frikiteam.events.resource.DistrictResource;
import com.frikiteam.events.resource.SaveDistrictResource;
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
@RequestMapping("/api/cities/{cityId}/districts")
public class CityDistrictsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private IDistrictService districtService;

    private District convertToEntity(SaveDistrictResource resource){
        return mapper.map(resource,District.class);
    }
    private DistrictResource convertToResource(District entity){
        return mapper.map(entity,DistrictResource.class);
    }

    @PostMapping
    @Operation(summary = "create a district by a city id", tags = {"city-districts"})
    public DistrictResource createDistrict(@PathVariable Long cityId, @Valid @RequestBody SaveDistrictResource resource){
        District district = convertToEntity(resource);
        return convertToResource(districtService.createDistrict(cityId, district));
    }

    @PutMapping("{districtId}")
    @Operation(summary = "update a district", tags = {"city-districts"})
    public DistrictResource updateDistrict(@PathVariable Long districtId,@RequestBody SaveDistrictResource resource){
        District district = convertToEntity(resource);
        return convertToResource(districtService.updateDistrict(districtId,district));
    }

    @DeleteMapping("{districtId}")
    @Operation(summary = "delete a district", tags = {"city-districts"})
    public ResponseEntity<?> deleteDistrict(@PathVariable Long districtId)
    {
        return districtService.deleteDistrict(districtId);
    }

    @GetMapping
    @Operation(summary = "Get all districts by city id", tags = {"city-districts"})
    public List<DistrictResource> getAllDistrictByCityId(@PathVariable Long cityId){
        return districtService.getAllDistrictsByCityId(cityId).stream()
                .map(district -> mapper.map(district, DistrictResource.class))
                .collect(Collectors.toList());
    }
}
