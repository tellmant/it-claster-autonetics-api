package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.SettlementType;

public interface SettlementTypeService {
    SettlementType create(SettlementType settlementType);
    SettlementType update(SettlementType settlementType);
    SettlementType readById(Integer id);
}
