package com.autonetics.autonetics.api.model.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Weather}
 */
public record WeatherDto(BigDecimal latitude, BigDecimal longitude, String city, String country,
                         double temperatureCelcius, double feelsLikeTemperatureCelcius, double windSpeed, int humidity,
                         double pressure, String weatherCondition, int isDay) implements Serializable {
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while converting WeatherDto object to JSON", e);
        }
    }
}