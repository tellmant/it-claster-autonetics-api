package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.StaffType;

import java.util.List;

public interface StaffTypeService {
    StaffType create(StaffType staffType);
    StaffType update(StaffType staffType);
    void delete(long id);
    StaffType readById(long id);
    List<StaffType> getAll();

}
