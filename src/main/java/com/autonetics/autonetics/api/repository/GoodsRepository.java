package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;
import java.util.Optional;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

    Optional<Goods> findByBarcode(String barcode);

    Optional<List<Goods>> findByGoodsTypeId_NameContains(String name);

    Optional<List<Goods>> findByClassID_NameContains(String name);

    Optional<List<Goods>> findByNameContains(String name);

    Optional<List<Goods>> findByProducerContains(String producer);

}