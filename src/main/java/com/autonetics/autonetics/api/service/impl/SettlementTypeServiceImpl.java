package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.exception.NullEntityReferenceException;
import com.autonetics.autonetics.api.model.entity.SettlementType;
import com.autonetics.autonetics.api.repository.SettlementTypeRepository;
import com.autonetics.autonetics.api.service.SettlementTypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SettlementTypeServiceImpl implements SettlementTypeService {
    private final SettlementTypeRepository settlementTypeRepository;
    @Override
    public SettlementType create(SettlementType settlementType) {
        if (settlementType != null) {
            return settlementTypeRepository.save(settlementType);
        }
        throw new NullEntityReferenceException("SettlementType cannot be 'null'");
    }

    @Override
    public SettlementType update(SettlementType settlementType) {
        if (settlementType != null) {
           return settlementTypeRepository.save(settlementType);
        }
        throw new NullEntityReferenceException("SettlementType cannot be 'null'");
    }

    @Override
    public SettlementType readById(Integer id) {
        return settlementTypeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Settlement with id " + id + " not found"));
    }
}
