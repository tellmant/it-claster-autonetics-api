package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StreetRepository extends JpaRepository<Street, Long> {
    List<Street> findAllByNameContaining(String name);
    List<Street> findAllBySlangNameContaining(String slangName);
}