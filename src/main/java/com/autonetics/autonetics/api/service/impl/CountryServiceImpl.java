package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.exception.NullEntityReferenceException;
import com.autonetics.autonetics.api.model.entity.Country;
import com.autonetics.autonetics.api.repository.CountryRepository;
import com.autonetics.autonetics.api.service.CountryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;
    @Override
    public Country findByCode(String code) {
        if (code != null) {
            return countryRepository.findByCode(code);
        }
        throw new NullEntityReferenceException("Code cannot be 'null'");
    }

    @Override
    public Country create(Country country) {
        if (country != null) {
            return countryRepository.save(country);
        }
        throw new NullEntityReferenceException("Country cannot be 'null'");
    }

    @Override
    public Country update(Country country) {
        if (country != null) {
            return countryRepository.save(country);
        }
        throw new NullEntityReferenceException("Country cannot be 'null'");
    }

    @Override
    public void delete(int id) {
        Country country = readById(id);
        countryRepository.delete(country);
    }

    @Override
    public Country readById(int id) {
        return countryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Country with id " + id + " not found"));
    }

    @Override
    public List<Country> getAll() {
        return countryRepository.findAll();
    }
}
