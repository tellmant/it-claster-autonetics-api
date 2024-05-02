package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.SettlementType}
 */
public record SettlementTypeDto(Integer id,
                                @NotNull(message = "Street type name can't be null") @Size(max = 50) String name,
                                @NotNull(message = "Street updated by can't be null") @Size(max = 50) String updatedBy,
                                @NotNull LocalDateTime updatedOn) implements Serializable {
}