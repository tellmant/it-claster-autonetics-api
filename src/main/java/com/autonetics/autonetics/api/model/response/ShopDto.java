package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Shop}
 */
public record ShopDto(
        Long id,
        @NotNull ClientDto client,
        @NotNull AddressDto address,
        @NotNull CustomerDto customer,
        @NotNull Boolean isParking,
        @NotNull Integer atFloor,
        @NotNull LocalDateTime updatedOn,
        @Size(max = 255) String updatedBy,
        @Size(max = 255) String name,
        @Size(max = 255) String photo,
        ShopTypeDto shopType
) implements Serializable {
}