package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Address}
 */
public record AddressDto(Long id, @NotNull(message = "Address name can't be null") @Size(max = 50) String name,
                         @NotNull AddressDto.SettlementShortDto settlement, StreetShortDto street,
                         @NotNull(message = "Address latitude can't be null") BigDecimal latitude,
                         @NotNull(message = "Address longitude can't be null") BigDecimal longitude,
                         @NotNull(message = "Address updated by can't be null") @Size(max = 50) String updatedBy,
                         @NotNull(message = "Address updated on can't be null") LocalDateTime updatedOn) implements Serializable {
    /**
     * DTO for {@link com.autonetics.autonetics.api.model.entity.Settlement}
     */
    public record SettlementShortDto(Long id, @NotNull @Size(max = 50) String name) implements Serializable {
    }

    /**
     * DTO for {@link com.autonetics.autonetics.api.model.entity.Street}
     */
    public record StreetShortDto(Long id, @NotNull @Size(max = 50) String name,
                                 @NotNull @Size(max = 50) String slangName) implements Serializable {
    }
}