package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.exception.NullEntityReferenceException;
import com.autonetics.autonetics.api.model.entity.DeliveryHistory;
import com.autonetics.autonetics.api.repository.DeliveryHistoryRepository;
import com.autonetics.autonetics.api.service.DeliveryHistoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryHistoryServiceImpl implements DeliveryHistoryService {

    private final DeliveryHistoryRepository deliveryHistoryRepository;


    @Override
    public DeliveryHistory create(DeliveryHistory deliveryHistory) {
        if (deliveryHistory != null) {
            return deliveryHistoryRepository.save(deliveryHistory);
        }
        throw new NullEntityReferenceException("DeliveryHistory cannot be 'null'.");
    }

    @Override
    public DeliveryHistory readById(long id) {
        return deliveryHistoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("DeliveryHistory with id " + id + " not found.")
        );
    }

    @Override
    public DeliveryHistory update(DeliveryHistory deliveryHistory) {
        if (deliveryHistory != null) {
            return deliveryHistoryRepository.save(deliveryHistory);
        }
        throw new NullEntityReferenceException("DeliveryHistory cannot be 'null'.");
    }

    @Override
    public void delete(long id) {
        DeliveryHistory deliveryHistory = readById(id);
        deliveryHistoryRepository.delete(deliveryHistory);
    }

    @Override
    public List<DeliveryHistory> getAll() {
        return deliveryHistoryRepository.findAll();
    }

    @Override
    public DeliveryHistory readByGoodsID_Barcode(String barcode) {
        return deliveryHistoryRepository.findByGoodsID_Barcode(barcode).orElseThrow(
                () -> new EntityNotFoundException("DeliveryHistory with barcode " + barcode + " not found.")
        );
    }

    @Override
    public DeliveryHistory readByDeliveryGoodsid_Barcode(String barcode) {
        return deliveryHistoryRepository.findByDeliveryGoodsid_Barcode(barcode).orElseThrow(
                () -> new EntityNotFoundException("DeliveryHistory with barcode " + barcode + " not found.")
        );
    }
}
