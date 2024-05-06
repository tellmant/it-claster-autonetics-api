package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.exception.NullEntityReferenceException;
import com.autonetics.autonetics.api.model.entity.StaffType;
import com.autonetics.autonetics.api.repository.StaffTypeRepository;
import com.autonetics.autonetics.api.service.StaffTypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class StaffTypeServiceImpl implements StaffTypeService {
    private final StaffTypeRepository staffTypeRepository;
    @Override
    public StaffType create(StaffType staffType) {
       if (staffType != null) {
            return staffTypeRepository.save(staffType);
        }
        throw new NullEntityReferenceException("StaffType cannot be 'null'.");
    }

    @Override
    public StaffType update(StaffType staffType) {
        if (staffType != null) {
            return staffTypeRepository.save(staffType);
        }
        throw new NullEntityReferenceException("StaffType cannot be 'null'.");
    }

    @Override
    public void delete(long id) {
        StaffType staffType = readById(id);
        staffTypeRepository.delete(staffType);
    }

    @Override
    public StaffType readById(long id) {
        return staffTypeRepository.findById(id).orElseThrow(
                ()->new EntityNotFoundException("StaffType with id " + id + " not found."));
    }

    @Override
    public List<StaffType> getAll() {
        return staffTypeRepository.findAll();
    }
}
