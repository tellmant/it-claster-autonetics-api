package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Shop}
 */

public record LocationAndDistanceDto(Long id, @Size(max = 255) String name, double distance, @NotNull Boolean isParking, @NotNull Integer atFloor,
                                     @NotNull AddressDto address, @NotNull CustomerDto customer,
                                     ShopTypeDto shopType) implements Serializable {
}