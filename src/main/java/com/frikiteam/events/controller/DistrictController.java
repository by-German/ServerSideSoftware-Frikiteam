package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.District;
import com.frikiteam.events.domain.service.IDistrictService;
import com.frikiteam.events.resource.DistrictResource;
import com.frikiteam.events.resource.SaveDistrictResource;
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
public class DistrictController {
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
    public Page<DistrictResource> getAllDistricts(Pageable pageable){
        List<DistrictResource> resources = districtService.getAllDistricts(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @PostMapping("/districts/create")
    public DistrictResource createDistrict(@Valid @RequestBody SaveDistrictResource resource){
        District district = convertToEntity(resource);
        return convertToResource(districtService.createDistrict(district));
    }

    @PutMapping("/districts/update/{districtId}")
    public DistrictResource updateDistrict(@PathVariable Long districtId,@RequestBody SaveDistrictResource resource){
        District district = convertToEntity(resource);
        return convertToResource(districtService.updateDistrict(districtId,district));
    }

    @DeleteMapping("/districts/delete/{districtId}")
    public ResponseEntity<?> deleteDistrict(@PathVariable Long districtId)
    {
        return districtService.deleteDistrict(districtId);
    }

    @GetMapping("/districts/get/{districtId}")
    public DistrictResource getDistrictById(@PathVariable Long districtId){
        return convertToResource(districtService.getDistrictById(districtId));
    }
}
