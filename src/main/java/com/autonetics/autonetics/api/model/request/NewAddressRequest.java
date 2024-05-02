package com.autonetics.autonetics.api.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Address}
 */
public record NewAddressRequest(@NotNull @Size(max = 50) String name,
                                @NotNull NewAddressRequest.SettlementDto settlement, StreetDto street,
                                BigDecimal latitude, BigDecimal longitude,
                                @NotNull @Size(max = 50) String updatedBy) implements Serializable {
    /**
     * DTO for {@link com.autonetics.autonetics.api.model.entity.Settlement}
     */
    public record SettlementDto(Long id, @NotNull @Size(max = 50) String name,
                                @NotNull @Size(max = 50) String updatedBy,
                                @NotNull LocalDateTime updatedOn) implements Serializable {
    }

    /**
     * DTO for {@link com.autonetics.autonetics.api.model.entity.Street}
     */
    public record StreetDto(Long id, @NotNull @Size(max = 50) String name,
                            @NotNull @Size(max = 50) String slangName) implements Serializable {
    }
}