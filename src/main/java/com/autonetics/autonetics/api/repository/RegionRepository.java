package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Integer> {
    List<Region> findAllByCountry_Id(int id);
    List<Region> findAllByCountry_Code(String code);

}