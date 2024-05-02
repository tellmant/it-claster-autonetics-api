package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.exception.NullEntityReferenceException;
import com.autonetics.autonetics.api.model.entity.Settlement;
import com.autonetics.autonetics.api.repository.SettlementRepository;
import com.autonetics.autonetics.api.service.SettlementService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SettlementServiceImpl implements SettlementService {
    private final SettlementRepository settlementRepository;

    @Override
    public Settlement readById(long id) {
        return settlementRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Settlement with id " + id + " not found"));
    }
    @Override
    public Settlement create(Settlement settlement) {
        if (settlement!= null) {
            return settlementRepository.save(settlement);
        }
        throw new NullEntityReferenceException("Settlement cannot be 'null'");
    }

    @Override
    public Settlement update(Settlement settlement) {
        if (settlement != null) {
            readById(settlement.getId());
            return settlementRepository.save(settlement);
        }
        throw new NullEntityReferenceException("Settlement cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        Settlement settlement = readById(id);
        settlementRepository.delete(settlement);
    }

    @Override
    public List<Settlement> getAll() {
        return settlementRepository.findAll();
    }

    @Override
    public List<Settlement> findAllByName(String name) {
        if (name == null || name.isEmpty()){
            throw new NullEntityReferenceException("Settlement name cannot be 'null'");
        }
        return settlementRepository.findAllByName(name);
    }

    @Override
    public List<Settlement> findAllBySettlementType_Id(Integer settlementTypeId) {
        if (settlementTypeId == null){
            throw new NullEntityReferenceException("Settlement type id cannot be 'null'");
        }
        return settlementRepository.findAllBySettlementType_Id(settlementTypeId);
    }

    @Override
    public List<Settlement> findAllBySettlementType_Name(String name) {
        if (name == null || name.isEmpty()){
            throw new NullEntityReferenceException("Settlement type name cannot be 'null'");
        }
        return settlementRepository.findAllBySettlementType_Name(name);
    }

    @Override
    public List<Settlement> findAllByDistrict_Id(Integer id) {
        if (id == null){
            throw new NullEntityReferenceException("District id cannot be 'null'");
        }
        return settlementRepository.findAllByDistrict_Id(id);
    }

    @Override
    public List<Settlement> findAllByDistrict_Name(String name) {
        if (name == null || name.isEmpty()){
            throw new NullEntityReferenceException("District name cannot be 'null'");
        }
        return settlementRepository.findAllByDistrict_Name(name);
    }

    @Override
    public List<Settlement> findAllByNameContainingAndDistrict_NameContaining(String name, String districtName) {
        if (name == null || name.isEmpty()){
            throw new NullEntityReferenceException("Settlement name cannot be 'null'");
        }
        if (districtName == null || districtName.isEmpty()){
            throw new NullEntityReferenceException("District name cannot be 'null'");
        }
        return settlementRepository.findAllByNameContainingAndDistrict_NameContaining(name, districtName);
    }

    @Override
    public List<Settlement> findAllByNameContainingAndDistrict_Id(String name, Integer districtId) {
        if (name == null || name.isEmpty()){
            throw new NullEntityReferenceException("Settlement name cannot be 'null'");
        }
        if (districtId == null){
            throw new NullEntityReferenceException("District id cannot be 'null'");
        }
        return settlementRepository.findAllByNameContainingAndDistrict_Id(name, districtId);
    }

    @Override
    public List<Settlement> findAllByNameContainingAndSettlementType_Id(String name, Integer settlementTypeId) {
        if (name == null || name.isEmpty()){
            throw new NullEntityReferenceException("Settlement name cannot be 'null'");
        }
        if (settlementTypeId == null){
            throw new NullEntityReferenceException("Settlement type id cannot be 'null'");
        }
        return settlementRepository.findAllByNameContainingAndSettlementType_Id(name, settlementTypeId);
    }

    @Override
    public List<Settlement> findAllByNameContainingAndSettlementType_NameContaining(String name, String settlementTypeName) {
        if (name == null || name.isEmpty()){
            throw new NullEntityReferenceException("Settlement name cannot be 'null'");
        }
        if (settlementTypeName == null || settlementTypeName.isEmpty()){
            throw new NullEntityReferenceException("Settlement type name cannot be 'null'");
        }
        return settlementRepository.findAllByNameContainingAndSettlementType_NameContaining(name, settlementTypeName);
    }

    @Override
    public List<Settlement> findAllByDistrict_NameContainingAndSettlementType_NameContaining(String districtName, String settlementTypeName) {
        if (districtName == null || districtName.isEmpty()){
            throw new NullEntityReferenceException("District name cannot be 'null'");
        }
        if (settlementTypeName == null || settlementTypeName.isEmpty()){
            throw new NullEntityReferenceException("Settlement type name cannot be 'null'");
        }
        return settlementRepository.findAllByDistrict_NameContainingAndSettlementType_NameContaining(districtName, settlementTypeName);
    }

    @Override
    public List<Settlement> findAllByDistrict_IdAndSettlementType_Id(Integer districtId, Integer settlementTypeId) {
        if (districtId == null){
            throw new NullEntityReferenceException("District id cannot be 'null'");
        }
        if (settlementTypeId == null){
            throw new NullEntityReferenceException("Settlement type id cannot be 'null'");
        }
        return settlementRepository.findAllByDistrict_IdAndSettlementType_Id(districtId, settlementTypeId);
    }
}
