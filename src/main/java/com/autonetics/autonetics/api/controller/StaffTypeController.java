package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.StaffTypeMapper;
import com.autonetics.autonetics.api.model.entity.StaffType;
import com.autonetics.autonetics.api.model.request.NewStaffTypeRequest;
import com.autonetics.autonetics.api.model.response.StaffTypeDto;
import com.autonetics.autonetics.api.service.StaffTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/staff-types")
public class StaffTypeController {
    private final StaffTypeService staffTypeService;
    private final StaffTypeMapper staffTypeMapper;

    @GetMapping
    public ResponseEntity<List<StaffTypeDto>> getAll() {
        return ResponseEntity.ok(staffTypeService.getAll().stream().map(staffTypeMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffTypeDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(staffTypeMapper.toDto(staffTypeService.readById(id)));
    }

    @PostMapping
    public ResponseEntity<StaffTypeDto> create(@RequestBody NewStaffTypeRequest newStaffType) {
        StaffType staffType = staffTypeMapper.toEntity(newStaffType);
        staffType.setUpdatedOn(LocalDateTime.now());
        return ResponseEntity.ok(staffTypeMapper.toDto(staffTypeService.create(staffType)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StaffTypeDto> update(@PathVariable long id, @RequestBody NewStaffTypeRequest newStaffType) {
        StaffType existingStaffType = staffTypeService.readById(id);
        StaffType updatedStaffType = staffTypeMapper.partialUpdate(newStaffType, existingStaffType);
        updatedStaffType.setUpdatedOn(LocalDateTime.now());
        StaffTypeDto updatedStaffTypeDto = staffTypeMapper.toDto(staffTypeService.update(updatedStaffType));
        return ResponseEntity.ok(updatedStaffTypeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        staffTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
