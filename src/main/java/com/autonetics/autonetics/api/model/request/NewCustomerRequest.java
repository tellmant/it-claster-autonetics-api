package com.autonetics.autonetics.api.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Customer}
 */
public record NewCustomerRequest(Long addressId, @NotNull Boolean onlineShoping,
                                    @NotNull String website,
                                 @Size(max = 255) String name) implements Serializable {
}