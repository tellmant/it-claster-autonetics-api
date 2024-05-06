package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    List<District> findAllByRegion_Id(int id);
    List<District> findAllByRegion_Name(String name);
}