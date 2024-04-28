package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.exception.NullEntityReferenceException;
import com.autonetics.autonetics.api.model.entity.Street;
import com.autonetics.autonetics.api.repository.StreetRepository;
import com.autonetics.autonetics.api.service.StreetService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StreetServiceImpl implements StreetService{
    private StreetRepository streetRepository;

    @Override
    public List<Street> findByName(String name) {
        if (name != null) {
            return streetRepository.findAllByNameContaining(name);
        }
        throw new NullEntityReferenceException("Name cannot be 'null'");
    }

    @Override
    public List<Street> findBySlangName(String slangName) {
        if (slangName != null) {
            return streetRepository.findAllBySlangNameContaining(slangName);
        }
        throw new NullEntityReferenceException("Slang name cannot be 'null'");
    }

    @Override
    public Street create(Street street) {
        if (street != null) {
            return streetRepository.save(street);
        }
        throw new NullEntityReferenceException("Street cannot be 'null'");
    }

    @Override
    public Street update(Street street) {
        if (street != null) {
            readById(street.getId());
            return streetRepository.save(street);
        }
        throw new NullEntityReferenceException("Street cannot be 'null'");
    }

    @Override
    public void delete(Long id) {
        Street street = readById(id);
        streetRepository.delete(street);
    }

    @Override
    public Street readById(Long id) {
        return streetRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Street with id " + id + " not found"));
    }

    @Override
    public List<Street> getAll() {
        return streetRepository.findAll();
    }
}
