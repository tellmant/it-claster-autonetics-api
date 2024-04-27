package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.model.entity.ShopType;
import com.autonetics.autonetics.api.repository.ShopTypeRepository;
import com.autonetics.autonetics.api.service.ShopTypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShopTypeServiceImpl implements ShopTypeService {
    private ShopTypeRepository shopTypeRepository;

    @Override
    public ShopType readById(long id) {
        return shopTypeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("ShopType with id " + id + " not found"));
    }

    @Override
    public ShopType create(ShopType shopType) {
        if (shopType != null) {
            return shopTypeRepository.save(shopType);
        }
//        throw new NullEntityReferenceException("ShopType cannot be 'null'");
        return null;
    }

    @Override
    public ShopType update(ShopType shopType) {
        if (shopType != null) {
            readById(shopType.getId());
            return shopTypeRepository.save(shopType);
        }
//        throw new NullEntityReferenceException("ShopType cannot be 'null'");
        return null;
    }

    @Override
    public void delete(long id) {
        ShopType shopType = readById(id);
        shopTypeRepository.delete(shopType);
    }

    @Override
    public List<ShopType> getAll() {
        return shopTypeRepository.findAll();
    }
}
