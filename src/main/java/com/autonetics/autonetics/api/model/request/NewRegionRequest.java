package com.autonetics.autonetics.api.model.request;

import com.autonetics.autonetics.api.model.response.CountryDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Region}
 */
public record NewRegionRequest(@NotNull(message = "Region name can't be null") @Size(max = 50) String name,
                               @NotNull CountryDto country) implements Serializable {
}