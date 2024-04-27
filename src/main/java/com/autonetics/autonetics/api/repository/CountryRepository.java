package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Country findByCode(String code);
}