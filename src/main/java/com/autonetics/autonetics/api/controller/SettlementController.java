package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.SettlementMapper;
import com.autonetics.autonetics.api.model.entity.Settlement;
import com.autonetics.autonetics.api.model.request.NewSettlementRequest;
import com.autonetics.autonetics.api.model.response.SettlementDto;
import com.autonetics.autonetics.api.service.SettlementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/location/settlements")
public class SettlementController {
    private final SettlementService settlementService;
    private final SettlementMapper settlementMapper;

    @GetMapping
    public ResponseEntity<List<SettlementDto>> getAll() {
        List<SettlementDto> settlements = settlementService.getAll().stream()
                .map(settlementMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(settlements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SettlementDto> readById(@PathVariable Long id) {
        SettlementDto settlementDto = settlementMapper.toDto(settlementService.readById(id));
        return ResponseEntity.ok(settlementDto);
    }

    @GetMapping("/name/{settlementName}")
    public ResponseEntity<List<SettlementDto>> findAllByName(@PathVariable String settlementName) {
        List<SettlementDto> settlements = settlementService.findAllByName(settlementName).stream()
                .map(settlementMapper::toDto)
                .collect(Collectors.toList());
        if (settlements.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(settlements);
    }

    @GetMapping("/settlement-type-id/{settlementTypeId}")
    public ResponseEntity<List<SettlementDto>> findAllBySettlementType_Id(@PathVariable Integer settlementTypeId) {
        List<SettlementDto> settlements = settlementService.findAllBySettlementType_Id(settlementTypeId).stream()
                .map(settlementMapper::toDto)
                .collect(Collectors.toList());
        if (settlements.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(settlements);
    }

    @GetMapping("/settlement-type-name/{settlementTypeName}")
    public ResponseEntity<List<SettlementDto>> findAllBySettlementType_Name(@PathVariable String settlementTypeName) {
        List<SettlementDto> settlements = settlementService.findAllBySettlementType_Name(settlementTypeName).stream()
                .map(settlementMapper::toDto)
                .collect(Collectors.toList());
        if (settlements.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(settlements);
    }

    @GetMapping("/district-id/{districtId}")
    public ResponseEntity<List<SettlementDto>> findAllByDistrict_Id(@PathVariable Integer districtId) {
        List<SettlementDto> settlements = settlementService.findAllByDistrict_Id(districtId).stream()
                .map(settlementMapper::toDto)
                .collect(Collectors.toList());
        if (settlements.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(settlements);
    }

    @GetMapping("/district-name/{districtName}")
    public ResponseEntity<List<SettlementDto>> findAllByDistrict_Name(@PathVariable String districtName) {
        List<SettlementDto> settlements = settlementService.findAllByDistrict_Name(districtName).stream()
                .map(settlementMapper::toDto)
                .collect(Collectors.toList());
        if (settlements.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(settlements);
    }

    @GetMapping("/name/{settlementName}/district-name/{districtName}")
    public ResponseEntity<List<SettlementDto>> findAllByNameContainingAndDistrict_NameContaining(@PathVariable String settlementName, @PathVariable String districtName) {
        List<SettlementDto> settlements = settlementService.findAllByNameContainingAndDistrict_NameContaining(settlementName, districtName).stream()
                .map(settlementMapper::toDto)
                .collect(Collectors.toList());
        if (settlements.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(settlements);
    }

    @GetMapping("/name/{settlementName}/district-id/{districtId}")
    public ResponseEntity<List<SettlementDto>> findAllByNameContainingAndDistrict_Id(@PathVariable String settlementName, @PathVariable Integer districtId) {
        List<SettlementDto> settlements = settlementService.findAllByNameContainingAndDistrict_Id(settlementName, districtId).stream()
                .map(settlementMapper::toDto)
                .collect(Collectors.toList());
        if (settlements.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(settlements);
    }

    @GetMapping("/name/{settlementName}/settlement-type-id/{settlementTypeId}")
    public ResponseEntity<List<SettlementDto>> findAllByNameContainingAndSettlementType_Id(@PathVariable String settlementName, @PathVariable Integer settlementTypeId) {
        List<SettlementDto> settlements = settlementService.findAllByNameContainingAndSettlementType_Id(settlementName, settlementTypeId).stream()
                .map(settlementMapper::toDto)
                .collect(Collectors.toList());
        if (settlements.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(settlements);
    }

    @GetMapping("/name/{settlementName}/settlement-type-name/{settlementTypeName}")
    public ResponseEntity<List<SettlementDto>> findAllByNameContainingAndSettlementType_NameContaining(@PathVariable String settlementName, @PathVariable String settlementTypeName) {
        List<SettlementDto> settlements = settlementService.findAllByNameContainingAndSettlementType_NameContaining(settlementName, settlementTypeName).stream()
                .map(settlementMapper::toDto)
                .collect(Collectors.toList());
        if (settlements.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(settlements);
    }

    @GetMapping("/district-name/{districtName}/settlement-type-name/{settlementTypeName}")
    public ResponseEntity<List<SettlementDto>> findAllByDistrict_NameContainingAndSettlementType_NameContaining(@PathVariable String districtName, @PathVariable String settlementTypeName) {
        List<SettlementDto> settlements = settlementService.findAllByDistrict_NameContainingAndSettlementType_NameContaining(districtName, settlementTypeName).stream()
                .map(settlementMapper::toDto)
                .collect(Collectors.toList());
        if (settlements.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(settlements);
    }

    @GetMapping("/district-id/{districtId}/settlement-type-id/{settlementTypeId}")
    public ResponseEntity<List<SettlementDto>> findAllByDistrict_IdAndSettlementType_Id(@PathVariable Integer districtId, @PathVariable Integer settlementTypeId) {
        List<SettlementDto> settlements = settlementService.findAllByDistrict_IdAndSettlementType_Id(districtId, settlementTypeId).stream()
                .map(settlementMapper::toDto)
                .collect(Collectors.toList());
        if (settlements.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(settlements);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        settlementService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<SettlementDto> create(@RequestBody NewSettlementRequest newSettlementRequest) {
        Settlement createdSettlement = settlementMapper.toEntity(newSettlementRequest);
        createdSettlement.setUpdatedOn(LocalDateTime.now());
        SettlementDto createdSettlementDto = settlementMapper.toDto(settlementService.create(createdSettlement));

        return ResponseEntity.ok(createdSettlementDto);
    }

    @PatchMapping("/{settlementId}")
    public ResponseEntity<SettlementDto> update(@PathVariable Long settlementId, @RequestBody NewSettlementRequest newSettlementRequest) {
        Settlement existingSettlement = settlementService.readById(settlementId);
        Settlement updatedSettlement = settlementMapper.toEntity(newSettlementRequest);
        updatedSettlement.setId(existingSettlement.getId());
        updatedSettlement.setUpdatedOn(LocalDateTime.now());
        SettlementDto updatedSettlementDto = settlementMapper.toDto(settlementService.update(updatedSettlement));

        return ResponseEntity.ok(updatedSettlementDto);
    }

}
