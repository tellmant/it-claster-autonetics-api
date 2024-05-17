package com.autonetics.autonetics.api.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Goods}
 */
public record RequestGoodsAi(Integer id, BigDecimal priceIn, BigDecimal priceOut, Float weight, @NotNull Integer rating,
                             @Size(max = 255) String name, @Size(max = 255) String description,
                             @Size(max = 255) String composition) implements Serializable {
}