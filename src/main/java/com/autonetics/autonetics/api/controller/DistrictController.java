package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.DistrictMapper;
import com.autonetics.autonetics.api.model.entity.District;
import com.autonetics.autonetics.api.model.request.NewDistrictRequest;
import com.autonetics.autonetics.api.model.response.DistrictDto;
import com.autonetics.autonetics.api.service.DistrictService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/location/districts")
public class DistrictController {
    private DistrictService districtService;
    private DistrictMapper districtMapper;

    @GetMapping
    public ResponseEntity<List<DistrictDto>> getAll() {
        List<DistrictDto> districts = districtService.getAll().stream()
                .map(districtMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(districts);
    }

    @GetMapping("/by-region-id/{regionId}")
    public ResponseEntity<List<DistrictDto>> getByRegionId(@PathVariable("regionId") int regionId) {
        List<DistrictDto> districts = districtService.findAllByRegionId(regionId).stream()
                .map(districtMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(districts);
    }

    @GetMapping("/by-region-name/{regionName}")
    public ResponseEntity<List<DistrictDto>> getByRegionName(@PathVariable("regionName") String regionName) {
        List<DistrictDto> districts = districtService.findAllByRegionName(regionName).stream()
                .map(districtMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(districts);
    }

    @GetMapping("/{districtId}")
    public ResponseEntity<DistrictDto> getById(@PathVariable("districtId") int districtId) {
        return ResponseEntity.ok(districtMapper.toDto(districtService.readById(districtId)));
    }

    @PostMapping
    public ResponseEntity<DistrictDto> create(@RequestBody NewDistrictRequest newDistrict) {
        if (newDistrict == null) {
            return ResponseEntity.badRequest().build();
        }
        District district = districtMapper.toEntity(newDistrict);
        district.setUpdatedOn(LocalDateTime.now());
        return ResponseEntity.ok(districtMapper.toDto(districtService.create(district)));
    }

        @PatchMapping("/{districtId}")
    public ResponseEntity<DistrictDto> update(@PathVariable("districtId") int districtId, @RequestBody NewDistrictRequest newDistrict) {
        if (newDistrict == null) {
            return ResponseEntity.badRequest().build();
        }

        District existingDistrict = districtService.readById(districtId);
        District updatedDistrict = districtMapper.partialUpdate(newDistrict, existingDistrict);
        updatedDistrict.setUpdatedOn(LocalDateTime.now());
        updatedDistrict = districtService.update(updatedDistrict);

        return ResponseEntity.ok(districtMapper.toDto(updatedDistrict));
    }

    @DeleteMapping("/{districtId}")
    public ResponseEntity<?> delete(@PathVariable("districtId") int districtId) {
        districtService.delete(districtId);
        return ResponseEntity.noContent().build();
    }
}
