package com.autonetics.autonetics.api.controller;


import com.autonetics.autonetics.api.mapper.CountryMapper;
import com.autonetics.autonetics.api.model.entity.Country;
import com.autonetics.autonetics.api.model.request.NewCountryRequest;
import com.autonetics.autonetics.api.model.response.CountryDto;
import com.autonetics.autonetics.api.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/location/countries")
public class CountryController {
    private CountryService countryService;
    private CountryMapper countryMapper;

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<CountryDto>> getAll() {
        List<Country> countries = countryService.getAll();
        return ResponseEntity.ok(countries.stream().map(countryMapper::toDto).collect(Collectors.toList()));
    }

    @GetMapping("/by-code/{code}")
    @Transactional(readOnly = true)
    public ResponseEntity<CountryDto> getByCode(@PathVariable("code") String code) {
        Country country = countryService.findByCode(code);
        return ResponseEntity.ok(countryMapper.toDto(country));
    }

    @GetMapping("/{countryId}")
    @Transactional(readOnly = true)
    public ResponseEntity<CountryDto> getById(@PathVariable("countryId") int countryId) {
        Country country = countryService.readById(countryId);
        return ResponseEntity.ok(countryMapper.toDto(country));
    }

    @PostMapping
    public ResponseEntity<CountryDto> create(@RequestBody NewCountryRequest newCountry) {
        if (newCountry == null) {
            return ResponseEntity.badRequest().build();
        }
        Country country = countryMapper.toEntity(newCountry);
        return ResponseEntity.ok(countryMapper.toDto(countryService.create(country)));
    }

    @PatchMapping("/{countryId}")
    public ResponseEntity<CountryDto> update(@PathVariable("countryId") int countryId, @RequestBody NewCountryRequest newCountry) {
        if (newCountry == null) {
            return ResponseEntity.badRequest().build();
        }
        Country currentCountry = countryService.readById(countryId);
        if (currentCountry == null) {
            return ResponseEntity.notFound().build();
        }
        countryMapper.partialUpdate(newCountry, currentCountry);
        Country updatedCountry = countryService.update(currentCountry);
        return ResponseEntity.ok(countryMapper.toDto(updatedCountry));
    }

    @DeleteMapping("/{countryId}")
    public ResponseEntity<Void> delete(@PathVariable("countryId") int countryId) {
        countryService.delete(countryId);
        return ResponseEntity.noContent().build();
    }
}
