package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.Region;

import java.util.List;

public interface RegionService {
    List<Region> findAllRegionsByCountryId(int id);
    List<Region> findAllRegionsByCountryCode(String code);
    Region readById(int id);
    Region create(Region region);
    Region update(Region region);
    void delete(int id);

}
