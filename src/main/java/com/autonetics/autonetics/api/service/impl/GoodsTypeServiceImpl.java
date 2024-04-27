package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.model.entity.GoodsType;
import com.autonetics.autonetics.api.repository.GoodsTypeRepository;
import com.autonetics.autonetics.api.service.GoodsTypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsTypeServiceImpl implements GoodsTypeService {

    private final GoodsTypeRepository goodsTypeRepository;

    @Override
    public GoodsType create(GoodsType goodsType) {
        if (goodsType != null) {
            return goodsTypeRepository.save(goodsType);
        }
        throw new NullEntityReferenceException("GoodsType cannot be 'null'.");
    }

    @Override
    public GoodsType readById(long id) {
        return goodsTypeRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("GoodsType with id " + id + " not found.a")
                );
    }

    @Override
    public GoodsType update(GoodsType goodsType) {
        if (goodsType != null) {
            return goodsTypeRepository.save(goodsType);
        }
        throw new NullEntityReferenceException("GoodsType cannot be 'null'.");
    }

    @Override
    public void delete(long id) {
        GoodsType goodsType = readById(id);
        goodsTypeRepository.delete(goodsType);
    }

    @Override
    public List<GoodsType> getAll() {
        return goodsTypeRepository.findAll();
    }
}
