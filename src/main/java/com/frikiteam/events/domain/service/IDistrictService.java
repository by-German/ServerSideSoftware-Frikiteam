package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IDistrictService {
    public District getDistrictById(Long districtId);

    public District createDistrict(District district);
    public District updateDistrict(Long districtId, District requestDistrict);
    public ResponseEntity<?> deleteDistrict (Long districtId);

    public Page<District> getAllDistricts(Pageable pageable);
    //public Page<District> getAllDistrictsByCityId(Long cityId, Pageable pageable);
}
