package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Settlement}
 */
public record SettlementDto(Long id, @NotNull(message = "Settlement name can't be null") @Size(max = 50) String name,
                            @NotNull(message = "Settlement type can't be null") SettlementTypeDto settlementType,
                            @NotNull(message = "District can't be null") DistrictDto district,
                            @NotNull(message = "Settlement updated by can't be null") @Size(max = 50) String updatedBy,
                            @NotNull(message = "Settlement updated on can't be null") LocalDateTime updatedOn) implements Serializable {
}