package com.autonetics.autonetics.api.model.request;

import com.autonetics.autonetics.api.model.response.GoodsTypeDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PatchGoods(
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
        @NotNull Long goodsTypeId,
        @Size(max = 50) String barcode
) {
}