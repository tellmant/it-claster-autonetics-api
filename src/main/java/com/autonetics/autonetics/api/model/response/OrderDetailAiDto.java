package com.autonetics.autonetics.api.model.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.OrderDetail}
 */
public record OrderDetailAiDto(
        Long goodsID,
        BigDecimal priceActual,
        Integer count,
        BigDecimal discount,
        BigDecimal amount,
        Instant addedDateTime
) implements Serializable {

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error while converting OrderDetailAiDto object to JSON");
        }
    }
}