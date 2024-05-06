package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.StaffMapper;
import com.autonetics.autonetics.api.model.entity.Shop;
import com.autonetics.autonetics.api.model.entity.Staff;
import com.autonetics.autonetics.api.model.entity.StaffType;
import com.autonetics.autonetics.api.model.request.NewStaffRequest;
import com.autonetics.autonetics.api.model.response.StaffDto;
import com.autonetics.autonetics.api.service.ShopService;
import com.autonetics.autonetics.api.service.StaffService;
import com.autonetics.autonetics.api.service.StaffTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/staff")
public class StaffController {
    private final StaffService staffService;
    private final StaffTypeService staffTypeService;
    private final ShopService shopService;
    private final StaffMapper staffMapper;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<StaffDto>> getAll() {
        return ResponseEntity.ok(staffService.getAll().stream().map(staffMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<StaffDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(staffMapper.toDto(staffService.readById(id)));
    }

    @GetMapping("/by-shop/{shopId}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<StaffDto>> getByShopId(@PathVariable long shopId) {
        return ResponseEntity.ok(staffService.getByShopId(shopId).stream().map(staffMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/by-staff-type/{staffTypeId}/and-shop/{shopId}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<StaffDto>> getByStaffTypeIdAndShopId(@PathVariable long staffTypeId, @PathVariable long shopId) {
        return ResponseEntity.ok(staffService.getByStaffTypeIdAndShopId(staffTypeId, shopId).stream().map(staffMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/by-email/{email}")
    @Transactional(readOnly = true)
    public ResponseEntity<StaffDto> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(staffMapper.toDto(staffService.getByEmail(email)));
    }

    @GetMapping("/by-phone-number/{phoneNumber}")
    @Transactional(readOnly = true)
    public ResponseEntity<StaffDto> getByPhoneNumber(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(staffMapper.toDto(staffService.getByPhoneNumber(phoneNumber)));
    }

    @GetMapping("/by-first-name-and-last-name")
    @Transactional(readOnly = true)
    public ResponseEntity<List<StaffDto>> getByFirstNameAndLastName(@RequestBody String firstName, @RequestBody String lastName) {
        return ResponseEntity.ok(staffService.getByFirstNameAndLastName(firstName, lastName).stream().map(staffMapper::toDto)
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<StaffDto> create(@RequestBody NewStaffRequest newStaff) {
        Staff staff = staffMapper.toEntity(newStaff);
        staff.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        staff.setUpdatedOn(LocalDateTime.now());
        if (staff.getShop() == null || staff.getStaffType() == null) {
            return ResponseEntity.badRequest().build();
        }
        staff.setPassword(passwordEncoder.encode(staff.getPassword()));

        return ResponseEntity.ok(staffMapper.toDto(staffService.create(staff)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StaffDto> update(@PathVariable long id, @RequestBody NewStaffRequest newStaff) {
        Staff staff = staffService.readById(id);
        staffMapper.partialUpdate(newStaff, staff);

        staff.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        staff.setUpdatedOn(LocalDateTime.now());
        if (newStaff.staffTypeId() != null) {
            StaffType staffType = staffTypeService.readById(newStaff.staffTypeId());
            staff.setStaffType(staffType);
        }

        if (newStaff.shopId() != null) {
            Shop shop = shopService.readById(newStaff.shopId());
            staff.setShop(shop);
        }
        if (staff.getShop() == null || staff.getStaffType() == null) {
            return ResponseEntity.badRequest().build();
        }

        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
        return ResponseEntity.ok(staffMapper.toDto(staffService.update(staff)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        staffService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
