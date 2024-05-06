package com.autonetics.autonetics.api.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Address}
 */
public record NewAddressRequest(@NotNull @Size(max = 50) String name, Long settlementId, Long streetId,
                                BigDecimal latitude, BigDecimal longitude) implements Serializable {
}