package com.autonetics.autonetics.api.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NewClassRequest (
        @NotBlank(message = "The 'name' field is required!") String name
) {
}
