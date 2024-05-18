package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.ShopMapper;
import com.autonetics.autonetics.api.model.entity.Shop;
import com.autonetics.autonetics.api.model.request.NewShopRequest;
import com.autonetics.autonetics.api.model.response.LocationAndDistanceDto;
import com.autonetics.autonetics.api.model.response.ShopDto;
import com.autonetics.autonetics.api.service.AddressService;
import com.autonetics.autonetics.api.service.CustomerService;
import com.autonetics.autonetics.api.service.ShopService;
import com.autonetics.autonetics.api.service.ShopTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/shops")
public class ShopController {
    private final ShopService shopService;
    private final ShopMapper shopMapper;
    private final ShopTypeService shopTypeService;
    private final AddressService addressService;
    private final CustomerService customerService;

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<ShopDto>> getAllShops() {
        return ResponseEntity.ok(shopService.getAll().stream()
                .map(shopMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/by-location")
    @Transactional(readOnly = true)
    public ResponseEntity<List<LocationAndDistanceDto>> getAllShopsIn500Meters(@RequestHeader BigDecimal latitude, @RequestHeader BigDecimal longitude) {
        List<LocationAndDistanceDto> locationList = shopService.getAllIn500Meters(latitude, longitude).stream()
                .map(shop -> {
                    LocationAndDistanceDto locationAndDistanceDto = shopMapper.toLocationAndDistance(shop);
                    double distance = calculateDistanceInMeters(latitude, longitude, shop.getAddress().getLatitude(), shop.getAddress().getLongitude());
                    return new LocationAndDistanceDto(
                            locationAndDistanceDto.id(),
                            locationAndDistanceDto.name(),
                            distance,
                            locationAndDistanceDto.isParking(),
                            locationAndDistanceDto.atFloor(),
                            locationAndDistanceDto.address(),
                            locationAndDistanceDto.customer(),
                            locationAndDistanceDto.shopType()

                    );
                }).sorted(Comparator.comparingDouble(LocationAndDistanceDto::distance))
                .toList();

        return ResponseEntity.ok(locationList);
    }

    @GetMapping("/by-address-id/{addressId}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<ShopDto>> getAllShopsByAddressId(@PathVariable Long addressId) {
        return ResponseEntity.ok(shopService.getAllByAddressId(addressId).stream()
                .map(shopMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/by-customer-id/{customerId}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<ShopDto>> getAllShopsByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(shopService.getAllByCustomerId(customerId).stream()
                .map(shopMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/by-shop-type-id/{shopTypeId}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<ShopDto>> getAllShopsByShopTypeId(@PathVariable Long shopTypeId) {
        return ResponseEntity.ok(shopService.getAllByShopTypeId(shopTypeId).stream()
                .map(shopMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/by-id/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<ShopDto> getShopById(@PathVariable Long id) {
        return ResponseEntity.ok(shopMapper.toDto(shopService.readById(id)));
    }

    @GetMapping("/by-name/{name}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<ShopDto>> getShopsByName(@PathVariable String name) {
        return ResponseEntity.ok(shopService.getAllByNameContaining(name).stream()
                .map(shopMapper::toDto)
                .collect(Collectors.toList()));
    }


    @PostMapping
    public ResponseEntity<ShopDto> createShop(@RequestBody NewShopRequest newShop) {
        if (newShop == null) {
            return ResponseEntity.badRequest().build();
        }
        Shop shop = shopMapper.toEntity(newShop);

        shop.setAddress(addressService.readById(newShop.addressId()));
        shop.setCustomer(customerService.readById(newShop.customerId()));
        shop.setShopType(shopTypeService.readById(newShop.shopTypeId()));

        shop.setUpdatedOn(LocalDateTime.now());
        shop.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());

        return ResponseEntity.ok(shopMapper.toDto(shopService.create(shop)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ShopDto> updateShop(@PathVariable Long id, @RequestBody NewShopRequest newShop) {
        if (newShop == null) {
            return ResponseEntity.badRequest().build();
        }

        Shop shop = shopService.readById(id);

        if (newShop.customerId() != null){
            shop.setCustomer(customerService.readById(newShop.customerId()));
        }

        if (newShop.shopTypeId() != null){
            shop.setShopType(shopTypeService.readById(newShop.shopTypeId()));
        }

        if (newShop.addressId() != null){
            shop.setAddress(addressService.readById(newShop.addressId()));
        }

        shopMapper.partialUpdate(newShop, shop);


        shop.setUpdatedOn(LocalDateTime.now());
        shop.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());

        return ResponseEntity.ok(shopMapper.toDto(shopService.update(shop)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable Long id) {
        shopService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private double calculateDistanceInMeters(BigDecimal lat1, BigDecimal lon1, BigDecimal lat2, BigDecimal lon2) {
        int earthRadiusKm = 6371;

        double latDistance = Math.toRadians(lat2.doubleValue() - lat1.doubleValue());
        double lonDistance = Math.toRadians(lon2.doubleValue() - lon1.doubleValue());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1.doubleValue())) * Math.cos(Math.toRadians(lat2.doubleValue()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadiusKm * c * 1000; // convert to meters
    }
}