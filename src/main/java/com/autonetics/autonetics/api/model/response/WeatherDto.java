package com.autonetics.autonetics.api.model.response;

import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Weather}
 */
public record WeatherDto(BigDecimal latitude, BigDecimal longitude, String city, String country,
                         double temperatureCelcius, double feelsLikeTemperatureCelcius, double windSpeed, int humidity,
                         double pressure, String weatherCondition, int isDay) implements Serializable {
}