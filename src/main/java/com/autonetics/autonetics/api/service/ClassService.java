package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.Class;

import java.util.List;

public interface ClassService {
    Class create(Class classEntity);
    Class readById(long id);
    Class update(Class classEntity);
    void delete(long id);
    List<Class> getAll();
}
