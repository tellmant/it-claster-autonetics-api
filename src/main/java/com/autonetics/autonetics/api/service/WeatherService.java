package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.Weather;

import java.math.BigDecimal;

public interface WeatherService {
    Weather getWeatherByCity(String city);
    Weather getWeatherByCoordinates(BigDecimal latitude, BigDecimal longitude);
}
