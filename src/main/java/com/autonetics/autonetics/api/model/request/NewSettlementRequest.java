package com.autonetics.autonetics.api.model.request;

import com.autonetics.autonetics.api.model.response.DistrictDto;
import com.autonetics.autonetics.api.model.response.SettlementTypeDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Settlement}
 */
public record NewSettlementRequest(@NotNull(message = "Settlement name can't be null") @Size(max = 50) String name,
                                   @NotNull(message = "(Settlement) settlement type can't be null") SettlementTypeDto settlementType,
                                   @NotNull(message = "(Settlement) district can't be null") DistrictDto district,
                                   @NotNull(message = "Settlement updated by can't be null") @Size(max = 50) String updatedBy) implements Serializable {
}