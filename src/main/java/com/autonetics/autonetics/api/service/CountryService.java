package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.Country;


public interface CountryService {
    Country findByCode(String code);
    Country create(Country country);
    Country update(Country country);
    void delete(int id);
    Country readById(int id);
}
