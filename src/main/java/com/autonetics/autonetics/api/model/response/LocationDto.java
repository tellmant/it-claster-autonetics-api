package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Address}
 */
public record LocationDto(@NotNull Long addressId,
                          @NotNull List<Long> shopIds,
                          @NotNull @Size(max = 50) String name,
                          @NotNull BigDecimal latitude,
                          @NotNull BigDecimal longitude) implements Serializable {
}