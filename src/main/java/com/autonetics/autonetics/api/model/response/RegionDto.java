package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Region}
 */
public record RegionDto(@NotNull(message = "Region id can't be null") Integer id,
                        @NotNull(message = "Region name can't be null") @Size(max = 50) String name,
                        @NotNull CountryDto country) implements Serializable {
}