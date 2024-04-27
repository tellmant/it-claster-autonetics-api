package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.RegionMapper;
import com.autonetics.autonetics.api.model.entity.Region;
import com.autonetics.autonetics.api.model.request.NewRegionRequest;
import com.autonetics.autonetics.api.model.response.RegionDto;
import com.autonetics.autonetics.api.service.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/location/regions")
public class RegionController {
    private RegionService regionService;
    private RegionMapper regionMapper;

    @GetMapping("/{regionId}")
    @Transactional(readOnly = true)
    public ResponseEntity<RegionDto> getById(@PathVariable("regionId") int regionId) {
        Region region = regionService.readById(regionId);
        if (region == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(regionMapper.toDto(region));
    }

    @GetMapping("/by-country-id/{countryId}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<RegionDto>> getByCountryId(@PathVariable("countryId") int countryId) {
        List<Region> regions = regionService.findAllRegionsByCountryId(countryId);
        if (regions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(regions.stream()
                .map(regionMapper::toDto).collect(Collectors.toList()));
    }

    @GetMapping("/by-country-code/{countryCode}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<RegionDto>> getByCountryCode(@PathVariable("countryCode") String countryCode) {
        List<Region> regions = regionService.findAllRegionsByCountryCode(countryCode);
        if (regions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(regions.stream()
                .map(regionMapper::toDto).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<RegionDto> create(@RequestBody NewRegionRequest newRegion) {
        if (newRegion == null) {
            return ResponseEntity.badRequest().build();
        }
        Region region = regionService.create(regionMapper.toEntity(newRegion));
        return ResponseEntity.ok(regionMapper.toDto(region));
    }

    @PatchMapping("/{regionId}")
    public ResponseEntity<RegionDto> update(@PathVariable("regionId") int regionId, @RequestBody NewRegionRequest newRegion) {
        Region currentRegion = regionService.readById(regionId);
        if (currentRegion == null) {
            return ResponseEntity.notFound().build();
        }
        regionMapper.partialUpdate(newRegion, currentRegion);
        Region updatedRegion = regionService.update(currentRegion);
        return ResponseEntity.ok(regionMapper.toDto(updatedRegion));
    }

    @DeleteMapping("/{regionId}")
    public ResponseEntity<?> delete(@PathVariable("regionId") int regionId) {
        regionService.delete(regionId);
        return ResponseEntity.noContent().build();
    }
}
