package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.AddressMapper;
import com.autonetics.autonetics.api.model.entity.Address;
import com.autonetics.autonetics.api.model.request.NewAddressRequest;
import com.autonetics.autonetics.api.model.response.AddressDto;
import com.autonetics.autonetics.api.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
        Address address = addressMapper.toEntity(newAddress);
        address.setUpdatedOn(LocalDateTime.now());
        Address createdAddress = addressService.create(address, newAddress.settlement().id(), newAddress.street().id());
        AddressDto createdAddressDto = addressMapper.toDto(createdAddress);
        return ResponseEntity.ok(createdAddressDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AddressDto> update(@PathVariable Long id, @RequestBody NewAddressRequest newAddress) {
        Address existingAddress = addressService.readById(id);
        Address updatedAddress = addressMapper.partialUpdate(newAddress, existingAddress);
        updatedAddress.setUpdatedOn(LocalDateTime.now());
        AddressDto updatedAddressDto = addressMapper.toDto(addressService.update(updatedAddress));
        return ResponseEntity.ok(updatedAddressDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
