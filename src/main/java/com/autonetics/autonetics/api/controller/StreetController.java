package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.StreetMapper;
import com.autonetics.autonetics.api.model.entity.Street;
import com.autonetics.autonetics.api.model.request.NewStreetRequest;
import com.autonetics.autonetics.api.model.response.StreetDto;
import com.autonetics.autonetics.api.service.StreetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/streets")
public class StreetController {
    private StreetService streetService;
    private StreetMapper streetMapper;

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<StreetDto>> getAll() {
        List<StreetDto> streets = streetService.getAll().stream()
                .map(streetMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(streets);
    }

    @GetMapping("/{streetId}")
    @Transactional(readOnly = true)
    public ResponseEntity<StreetDto> getById(@PathVariable("streetId") long streetId) {
        Street street = streetService.readById(streetId);
        return ResponseEntity.ok(streetMapper.toDto(street));
    }

    @GetMapping("by-name/{name}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<StreetDto>> getByName(@PathVariable("name") String name) {
        List<StreetDto> streets = streetService.findBySlangName(name).stream()
                .map(streetMapper::toDto).collect(Collectors.toList());
        if (streets.isEmpty()) {
            streets = streetService.findByName(name).stream()
                    .map(streetMapper::toDto)
                    .collect(Collectors.toList());
            if (streets.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(streets);
        }
        return ResponseEntity.ok(streets);
    }

    @PatchMapping("/{streetId}")
    public ResponseEntity<StreetDto> update(@PathVariable("streetId") long streetId, @RequestBody NewStreetRequest newStreetRequest) {
        if (streetService.readById(streetId) == null) {
            return ResponseEntity.notFound().build();
        }
        Street street = streetMapper.toEntity(newStreetRequest);
        street.setId(streetId);
        return ResponseEntity.ok(streetMapper.toDto(streetService.update(street)));
    }

    @PostMapping
    public ResponseEntity<StreetDto> create(@RequestBody NewStreetRequest newStreetRequest) {
        Street street = streetMapper.toEntity(newStreetRequest);
        return ResponseEntity.ok(streetMapper.toDto(streetService.create(street)));
    }

    @DeleteMapping("/{streetId}")
    public ResponseEntity<Void> delete(@PathVariable("streetId") long streetId) {
        streetService.delete(streetId);
        return ResponseEntity.noContent().build();
    }
}
