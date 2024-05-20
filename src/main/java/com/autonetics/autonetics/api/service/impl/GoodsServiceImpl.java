package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.exception.NullEntityReferenceException;
import com.autonetics.autonetics.api.model.entity.Goods;
import com.autonetics.autonetics.api.repository.GoodsRepository;
import com.autonetics.autonetics.api.service.GoodsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {

    private final GoodsRepository goodsRepository;

    @Override
    public Goods create(Goods goods) {
        if (goods != null) {
            return goodsRepository.save(goods);
        }
        throw new NullEntityReferenceException("Goods cannot be 'null'.");
    }

    @Override
    public Goods readById(long id) {
        return goodsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Goods with id " + id + " not found."));
    }

    @Override
    public Goods update(Goods goods) {
        if (goods != null) {
            return goodsRepository.save(goods);
        }
        throw new NullEntityReferenceException("Goods cannot be 'null'.");
    }

    @Override
    public void delete(long id) {
        Goods goods = readById(id);
        goodsRepository.delete(goods);
    }

    @Override
    public List<Goods> getAll() {
        return goodsRepository.findAll();
    }

    @Override
    public Goods readByBarcode(String barcode) {
        return goodsRepository.findByBarcode(barcode)
                .orElseThrow(
                        () -> new EntityNotFoundException("Goods with barcode " + barcode + " not found.")
                );
    }

    @Override
    public List<Goods> readByGoodsTypeId_Name(String name) {
        return goodsRepository.findByGoodsTypeId_NameContains(name)
                .orElseThrow(
                        () -> new EntityNotFoundException("Goods with type name " + name + " not found.")
                );
    }

    @Override
    public List<Goods> readByClassID_Name(String name) {
        return goodsRepository.findByClassID_NameContains(name)
                .orElseThrow(
                        () -> new EntityNotFoundException("Goods with class name " + name + " not found.")
                );
    }

    @Override
    public List<Goods> readByName(String name) {
        return goodsRepository.findByNameContains(name)
                .orElseThrow(
                        () -> new EntityNotFoundException("Goods with name " + name + " not found.")
                );
    }

    @Override
    public List<Goods> readByProducer(String producer) {
        return goodsRepository.findByProducerContains(producer)
                .orElseThrow(
                        () -> new EntityNotFoundException("Goods with producer " + producer + " not found.")
                );
    }
}
