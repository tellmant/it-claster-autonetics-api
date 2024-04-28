package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.District;

import java.util.List;

public interface DistrictService {
    District create(District district);

    District readById(int id);

    District update(District district);

    void delete(int id);

    List<District> getAll();

    List<District> findAllByRegionId(int id);

    List<District> findAllByRegionName(String name);
}
