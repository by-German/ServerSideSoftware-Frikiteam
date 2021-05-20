package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.District;
import com.frikiteam.events.domain.repositories.DistrictRepository;
import com.frikiteam.events.domain.service.IDistrictService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImplementation implements IDistrictService {
    @Autowired
    private DistrictRepository districtRepository;


    @Override
    public District getDistrictById(Long districtId) {
        return districtRepository.findById(districtId)
                .orElseThrow(()->new ResourceNotFoundException("District", "id", districtId));
    }

    @Override
    public District createDistrict(District district) {
        return districtRepository.save(district);
    }

    @Override
    public District updateDistrict(Long districtId, District requestDistrict) {
        District district = districtRepository.findById(districtId)
                .orElseThrow(()->new ResourceNotFoundException("District", "id", districtId));
        district.setName(requestDistrict.getName());
        return districtRepository.save(district);
    }

    @Override
    public ResponseEntity<?> deleteDistrict(Long districtId) {
        District district = districtRepository.findById(districtId)
                .orElseThrow(()->new ResourceNotFoundException("District", "id", districtId));
        districtRepository.delete(district);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<District> getAllDistricts(Pageable pageable) {
        return districtRepository.findAll(pageable);
    }

}
