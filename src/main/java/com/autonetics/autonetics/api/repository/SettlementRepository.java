package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SettlementRepository extends JpaRepository<Settlement, Long> {
    List<Settlement> findAllByName(String name); // за назвою
    List<Settlement> findAllBySettlementType_Id(Integer id); // за типом населеного пункту
    List<Settlement> findAllBySettlementType_Name(String name); // за типом населеного пункту (назва типу)
    List<Settlement> findAllByDistrict_Id(Integer districtId); // за областю
    List<Settlement> findAllByDistrict_Name(String name); // за областю (назва області)
    List<Settlement> findAllByNameContainingAndDistrict_NameContaining(String name, String districtName); // за назвою та назвою області
    List<Settlement> findAllByNameContainingAndDistrict_Id(String name, Integer districtId); // за назвою та областю
    List<Settlement> findAllByNameContainingAndSettlementType_Id(String name, Integer settlementTypeId); // за назвою та типом населеного пункту
    List<Settlement> findAllByNameContainingAndSettlementType_NameContaining(String name, String settlementTypeName); // за назвою та типом населеного пункту (назва типу)
    List<Settlement> findAllByDistrict_NameContainingAndSettlementType_NameContaining(String districtName, String settlementTypeName); // назва області та типу населеного пункту
    List<Settlement> findAllByDistrict_IdAndSettlementType_Id(Integer districtId, Integer settlementTypeId); // за областю та типом населеного пункту
}