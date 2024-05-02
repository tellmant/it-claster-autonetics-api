package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.Street;

import java.util.List;


public interface StreetService {
    List<Street> findByName(String name);
    List<Street> findBySlangName(String slangName);
    Street create(Street street);
    Street update(Street street);
    void delete(Long id);
    Street readById(Long id);
    List<Street> getAll();
}
