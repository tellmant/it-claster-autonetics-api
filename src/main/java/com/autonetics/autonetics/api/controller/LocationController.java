package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.AddressMapper;
import com.autonetics.autonetics.api.model.response.LocationDto;
import com.autonetics.autonetics.api.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/location")
public class LocationController {
    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @GetMapping
    public ResponseEntity<List<LocationDto>> getLocations(@RequestHeader("latitude") BigDecimal latitude, @RequestHeader("longitude") BigDecimal longitude) {
        return ResponseEntity.ok(
                addressService.findLocationsWithin100Meters(latitude, longitude)
                        .stream()
                        .map(addressMapper::toLocationDto)
                        .collect(java.util.stream.Collectors.toList())
        );
    }
}
