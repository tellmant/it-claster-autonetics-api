package com.autonetics.autonetics.api.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
public record NewGoodsType(
        @NotBlank(message = "The 'name' field is required!") String name,
        @NotNull(message = "The 'updatedBy' cannot be null!") String updatedBy
) {
}
