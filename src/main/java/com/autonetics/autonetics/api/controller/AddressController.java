package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.AddressMapper;
import com.autonetics.autonetics.api.model.entity.Address;
import com.autonetics.autonetics.api.model.request.NewAddressRequest;
import com.autonetics.autonetics.api.model.response.AddressDto;
import com.autonetics.autonetics.api.service.AddressService;
import com.autonetics.autonetics.api.service.SettlementService;
import com.autonetics.autonetics.api.service.StreetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/location/addresses")
public class AddressController {
    private final AddressService addressService;
    private final AddressMapper addressMapper;
    private final SettlementService settlementService;
    private final StreetService streetService;

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAll() {
        List<AddressDto> addresses = addressService.getAll().stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> readById(@PathVariable Long id) {
        AddressDto addressDto = addressMapper.toDto(addressService.readById(id));
        return ResponseEntity.ok(addressDto);
    }

    @GetMapping("/latitude/{latitude}/longitude/{longitude}")
    public ResponseEntity<AddressDto> findByLatitudeAndLongitude(@PathVariable BigDecimal latitude, @PathVariable BigDecimal longitude) {
        AddressDto addressDto = addressMapper.toDto(addressService.findByLatitudeAndLongitude(latitude, longitude));
        return ResponseEntity.ok(addressDto);
    }

    @GetMapping("/street/{streetId}")
    public ResponseEntity<List<AddressDto>> findAllByStreet_Id(@PathVariable Long streetId) {
        List<AddressDto> addresses = addressService.findAllByStreet_Id(streetId).stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(addresses);
    }

    @PostMapping
    public ResponseEntity<AddressDto> create(@RequestBody NewAddressRequest newAddress) {
        if (newAddress == null || newAddress.settlementId() == null || newAddress.streetId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Address createdAddress = addressMapper.toEntity(newAddress);
        createdAddress.setSettlement(settlementService.readById(newAddress.settlementId()));
        createdAddress.setStreet(streetService.readById(newAddress.streetId()));
        createdAddress.setUpdatedOn(LocalDateTime.now());
        createdAddress.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        AddressDto createdAddressDto = addressMapper.toDto(addressService.create(createdAddress));

        return ResponseEntity.ok(createdAddressDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AddressDto> update(@PathVariable Long id, @RequestBody NewAddressRequest newAddress) {
        if (newAddress == null) {
            return ResponseEntity.badRequest().build();
        }

        Address existingAddress = addressService.readById(id);
        if (newAddress.settlementId() != null) {
            existingAddress.setSettlement(settlementService.readById(newAddress.settlementId()));
        }
        if (newAddress.streetId() != null) {
            existingAddress.setStreet(streetService.readById(newAddress.streetId()));
        }
        Address updatedAddress = addressMapper.partialUpdate(newAddress, existingAddress);
        updatedAddress.setUpdatedOn(LocalDateTime.now());
        updatedAddress.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        AddressDto updatedAddressDto = addressMapper.toDto(addressService.update(updatedAddress));

        return ResponseEntity.ok(updatedAddressDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
