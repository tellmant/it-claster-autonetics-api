package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Goods}
 */
public record GoodsDto(
        Integer id,
        @NotNull @Size(max = 50) String name,
        BigDecimal priceIn,
        BigDecimal priceOut,
        BigDecimal priceAI,
        Float weight,
        @NotNull @Size(max = 300) String description,
        @NotNull @Size(max = 50) String producer,
        Long countryID,
        @NotNull @Size(max = 100) String storageCondition,
        @NotNull @Size(max = 1000) String composition,
        Long supplierID,
        @NotNull Integer rating,
        Long classID,
        @NotNull GoodsTypeDto goodsTypeId,
        @Size(max = 50) String barcode,
        @NotNull @Size(max = 50) String updatedBy,
        Instant updatedOn
) implements Serializable {
}