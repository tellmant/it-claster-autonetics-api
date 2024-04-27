package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Street}
 */
public record StreetDto(Long id, @NotNull(message = "Street name can't be null") @Size(max = 50) String name,
                        @Size(max = 50) String slangName, @NotNull StreetTypeDto streetType) implements Serializable {
}