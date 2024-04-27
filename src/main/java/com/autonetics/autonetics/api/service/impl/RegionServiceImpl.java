package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.exception.NullEntityReferenceException;
import com.autonetics.autonetics.api.model.entity.Region;
import com.autonetics.autonetics.api.repository.RegionRepository;
import com.autonetics.autonetics.api.service.RegionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RegionServiceImpl implements RegionService {
    private RegionRepository regionRepository;

    @Override
    public List<Region> findAllRegionsByCountryId(int id) {
        List<Region> allRegionsById = regionRepository.findAllByCountry_Id(id);
        if (allRegionsById.isEmpty()) {
            throw new EntityNotFoundException("Regions with country id " + id + " not found");
        }
        return allRegionsById;
    }

    @Override
    public List<Region> findAllRegionsByCountryCode(String code) {
        List<Region> allRegionsByCode = regionRepository.findAllByCountry_Code(code);
        if (allRegionsByCode.isEmpty()) {
            throw new EntityNotFoundException("Regions with country code " + code + " not found");
        }
        return allRegionsByCode;
    }

    @Override
    public Region readById(int id) {
        return regionRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Region with id " + id + " not found"));
    }

    @Override
    public Region create(Region region) {
        if (region != null) {
            return regionRepository.save(region);
        }
        throw new NullEntityReferenceException("Region cannot be 'null'");
    }

    @Override
    public Region update(Region region) {
        if (region != null) {
            readById(region.getId());
            return regionRepository.save(region);
        }
        throw new NullEntityReferenceException("Region cannot be 'null'");
    }

    @Override
    public void delete(int id) {
        Region region = readById(id);
        regionRepository.delete(region);
    }
}
