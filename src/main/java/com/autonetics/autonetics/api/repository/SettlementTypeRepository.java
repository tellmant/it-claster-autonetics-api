package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.SettlementType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettlementTypeRepository extends JpaRepository<SettlementType, Integer> {
}