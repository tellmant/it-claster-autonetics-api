package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.Goods;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface GoodsRepository extends JpaRepositoryImplementation<Goods, Long> {
}