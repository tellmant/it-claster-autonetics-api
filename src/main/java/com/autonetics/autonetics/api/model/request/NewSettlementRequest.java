package com.autonetics.autonetics.api.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Settlement}
 */
public record NewSettlementRequest(@NotNull @Size(max = 50) String name, Integer settlementTypeId,
                                   Integer districtId) implements Serializable {
}