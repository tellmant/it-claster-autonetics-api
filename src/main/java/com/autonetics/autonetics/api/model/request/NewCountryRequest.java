package com.autonetics.autonetics.api.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Country}
 */
public record NewCountryRequest(@NotNull @Size(max = 50) String name,
                                @NotNull(message = "Country code can't be null") @Size(message = "Country code can't be more than 3 characters long", max = 3) String code) implements Serializable {
}