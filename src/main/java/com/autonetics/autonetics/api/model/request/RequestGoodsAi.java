package com.autonetics.autonetics.api.model.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Goods}
 */
public record RequestGoodsAi(Integer id, BigDecimal priceIn, BigDecimal priceOut, Float weight, @NotNull Integer rating,
                             @Size(max = 255) String name, @Size(max = 255) String description,
                             @Size(max = 255) String composition) implements Serializable {
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while converting RequestGoodsAi object to JSON", e);
        }
    }
}