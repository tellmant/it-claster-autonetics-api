package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.Settlement;

import java.util.List;

public interface SettlementService {
    Settlement readById(long id);
    Settlement create(Settlement settlement);
    Settlement update(Settlement settlement);
    void delete(long id);
    List<Settlement> getAll();
    List<Settlement> findAllByName(String name);
    List<Settlement> findAllBySettlementType_Id(Integer settlementTypeId);
    List<Settlement> findAllBySettlementType_Name(String name);
    List<Settlement> findAllByDistrict_Id(Integer districtId);
    List<Settlement> findAllByDistrict_Name(String name);
    List<Settlement> findAllByNameContainingAndDistrict_NameContaining(String name, String districtName);
    List<Settlement> findAllByNameContainingAndDistrict_Id(String name, Integer districtId);
    List<Settlement> findAllByNameContainingAndSettlementType_Id(String name, Integer settlementTypeId);
    List<Settlement> findAllByNameContainingAndSettlementType_NameContaining(String name, String settlementTypeName);
    List<Settlement> findAllByDistrict_NameContainingAndSettlementType_NameContaining(String districtName, String settlementTypeName);
    List<Settlement> findAllByDistrict_IdAndSettlementType_Id(Integer districtId, Integer settlementTypeId);

}
