package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.GoodsType}
 */
public record GoodsTypeDto(
        @NotNull(message = "The 'id' cannot be null!") Long id,
        String name,
        @NotNull(message = "The 'updatedBy' cannot be null!") String updatedBy,
        @NotNull(message = "The 'updatedOn' cannot be null!") Instant updatedOn
) implements Serializable {
}