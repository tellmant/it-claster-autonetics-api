package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.exception.NullEntityReferenceException;
import com.autonetics.autonetics.api.model.entity.District;
import com.autonetics.autonetics.api.repository.DistrictRepository;
import com.autonetics.autonetics.api.service.DistrictService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

    @Override
    public District create(District district) {
        if (district != null) {
            return districtRepository.save(district);
        }
        throw new NullEntityReferenceException("District cannot be 'null'");
    }

    @Override
    public District readById(int id) {
        return districtRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("District with id " + id + " not found"));
    }

    @Override
    public District update(District district) {
        if (district != null) {
            readById(district.getId());
            return districtRepository.save(district);
        }
        throw new NullEntityReferenceException("District cannot be 'null'");
    }

    @Override
    public void delete(int id) {
        District district = readById(id);
        districtRepository.delete(district);
    }

    @Override
    public List<District> getAll() {
        return districtRepository.findAll();
    }

    @Override
    public List<District> findAllByRegionId(int id) {
        return districtRepository.findAllByRegion_Id(id);
    }

    @Override
    public List<District> findAllByRegionName(String name) {
        return districtRepository.findAllByRegion_Name(name);
    }
}
