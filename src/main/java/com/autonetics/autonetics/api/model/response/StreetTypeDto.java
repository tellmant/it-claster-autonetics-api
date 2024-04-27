package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.StreetType}
 */
public record StreetTypeDto(@NotNull Long id,
                            @NotNull(message = "Street type name can't be null") @Size(max = 50) String name) implements Serializable {
}