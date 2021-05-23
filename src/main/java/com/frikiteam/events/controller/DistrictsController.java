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
@RequestMapping("/api")
public class DistrictsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private IDistrictService districtService;
    //Conversiones
    private District convertToEntity(SaveDistrictResource resource){
        return mapper.map(resource,District.class);
    }
    private DistrictResource convertToResource(District entity){
        return mapper.map(entity,DistrictResource.class);
    }

    @GetMapping("/districts")
    @Operation(summary = "get all districts by pages", tags = {"districts"})
    public Page<DistrictResource> getAllDistricts(Pageable pageable){
        List<DistrictResource> resources = districtService.getAllDistricts(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @PostMapping("/cities/{cityId}/districts")
    @Operation(summary = "create a district by a city id", tags = {"districts"})
    public DistrictResource createDistrict(@PathVariable Long cityId, @Valid @RequestBody SaveDistrictResource resource){
        District district = convertToEntity(resource);
        return convertToResource(districtService.createDistrict(cityId, district));
    }

    @PutMapping("/districts/{districtId}")
    @Operation(summary = "update a district", tags = {"districts"})
    public DistrictResource updateDistrict(@PathVariable Long districtId,@RequestBody SaveDistrictResource resource){
        District district = convertToEntity(resource);
        return convertToResource(districtService.updateDistrict(districtId,district));
    }

    @DeleteMapping("/districts/{districtId}")
    @Operation(summary = "delete a district", tags = {"districts"})
    public ResponseEntity<?> deleteDistrict(@PathVariable Long districtId)
    {
        return districtService.deleteDistrict(districtId);
    }

    @GetMapping("/districts/{districtId}")
    @Operation(summary = "Get a district by id", tags = {"districts"})
    public DistrictResource getDistrictById(@PathVariable Long districtId){
        return convertToResource(districtService.getDistrictById(districtId));
    }
}
