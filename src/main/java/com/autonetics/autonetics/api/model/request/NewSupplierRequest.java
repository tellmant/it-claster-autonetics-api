package com.autonetics.autonetics.api.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewSupplierRequest(
        @NotNull(message = "The 'phoneNumber' field cannot be null.") @Size(max = 15) String phoneNumber,
        @NotNull(message = "The 'email' field cannot be null.") String email,
        @NotNull(message = "The 'bankAccount' field cannot be null.") String bankAccount,
        @NotNull(message = "The 'name' field cannot be null.") String name
) {
}
