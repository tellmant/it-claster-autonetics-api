package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.exception.NullEntityReferenceException;
import com.autonetics.autonetics.api.model.entity.DeliveryGoods;
import com.autonetics.autonetics.api.repository.DeliveryGoodsRepository;
import com.autonetics.autonetics.api.service.DeliveryGoodsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryGoodsServiceImpl implements DeliveryGoodsService {

    private final DeliveryGoodsRepository deliveryGoodsRepository;

    @Override
    public DeliveryGoods create(DeliveryGoods deliveryGoods) {
        if (deliveryGoods != null) {
            return deliveryGoodsRepository.save(deliveryGoods);
        }
        throw new NullEntityReferenceException("DeliveryGoods cannot be 'null'.");
    }

    @Override
    public DeliveryGoods readById(long id) {
        return deliveryGoodsRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("DeliveryGoods with id " + id + " not found.")
                );
    }

    @Override
    public DeliveryGoods update(DeliveryGoods deliveryGoods) {
        if (deliveryGoods != null) {
            return deliveryGoodsRepository.save(deliveryGoods);
        }
        throw new NullEntityReferenceException("DeliveryGoods cannot be 'null'.");
    }

    @Override
    public void delete(long id) {
        DeliveryGoods deliveryGoods = readById(id);
        deliveryGoodsRepository.delete(deliveryGoods);
    }

    @Override
    public List<DeliveryGoods> getAll() {
        return deliveryGoodsRepository.findAll();
    }

    @Override
    public DeliveryGoods readByBarcode(String barcode) {
        return deliveryGoodsRepository.findByBarcode(barcode)
                .orElseThrow(
                        () -> new EntityNotFoundException("DeliveryGoods with barcode " + barcode + " not found.")
                );
    }

    @Override
    public DeliveryGoods readByNumber(Integer number) {
        return deliveryGoodsRepository.findByNumber(number)
                .orElseThrow(
                        () -> new EntityNotFoundException("DeliveryGoods with number " + number + " not found.")
                );
    }

    @Override
    public List<DeliveryGoods> readBySupplierNameContains(String name) {
        return deliveryGoodsRepository.findBySupplierID_NameContains(name)
                .orElseThrow(
                        () -> new EntityNotFoundException("DeliveryGoods with supplier name " + name + " not found.")
                );
    }

    @Override
    public List<DeliveryGoods> readByGoodsTypeNameContains(String name) {
        return deliveryGoodsRepository.findByGoodsID_GoodsTypeId_NameContains(name)
                .orElseThrow(
                        () -> new EntityNotFoundException("DeliveryGoods with goods type name " + name + " not found.")
                );
    }

    @Override
    public DeliveryGoods readByGoodsBarcode(String barcode) {
        return deliveryGoodsRepository.findByGoodsID_Barcode(barcode)
                .orElseThrow(
                        () -> new EntityNotFoundException("DeliveryGoods with goods barcode " + barcode + " not found.")
                );
    }

    @Override
    public List<DeliveryGoods> readByGoodsNameContains(String name) {
        return deliveryGoodsRepository.findByGoodsID_NameContains(name)
                .orElseThrow(
                        () -> new EntityNotFoundException("DeliveryGoods with goods name " + name + " not found.")
                );
    }

    @Override
    public List<DeliveryGoods> readByGoodsProducerContains(String producer) {
        return deliveryGoodsRepository.findByGoodsID_ProducerContains(producer)
                .orElseThrow(
                        () -> new EntityNotFoundException("DeliveryGoods with goods producer " + producer + " not found.")
                );
    }
}
