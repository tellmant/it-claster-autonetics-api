package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.AddressMapper;
import com.autonetics.autonetics.api.mapper.ShopMapper;
import com.autonetics.autonetics.api.model.response.LocationDto;
import com.autonetics.autonetics.api.model.entity.Shop;
import com.autonetics.autonetics.api.service.AddressService;
import com.autonetics.autonetics.api.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/location")
public class LocationController {
    private final AddressService addressService;
    private final ShopService shopService;

    @GetMapping
    public ResponseEntity<List<LocationDto>> getLocations(@RequestHeader("latitude") BigDecimal latitude,
                                                          @RequestHeader("longitude") BigDecimal longitude) {
        List<LocationDto> locations = addressService.findLocationsWithin500Meters(latitude, longitude)
                .stream()
                .map(address -> {
                    List<Long> shopIds = shopService.getAllByAddressId(address.getId())
                            .stream()
                            .map(Shop::getId)
                            .collect(Collectors.toList());
                    return new LocationDto(address.getId(), shopIds, address.getName(), address.getLatitude(), address.getLongitude());
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(locations);
    }
}
