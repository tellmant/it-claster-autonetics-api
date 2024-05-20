package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.mapper.WeatherMapper;
import com.autonetics.autonetics.api.model.entity.Weather;
import com.autonetics.autonetics.api.model.response.WeatherApiResponse;
import com.autonetics.autonetics.api.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    @Value(value = "${WEATHER_API_KEY}")
    private String weatherApiKey;

    private static final String WEATHER_URL = "https://api.weatherapi.com/v1/current.json?key=";
    private final WeatherMapper weatherMapper;
    private final ObjectMapper objectMapper;

    @Override
    public Weather getWeatherByCity(String city) {
        String url = WEATHER_URL + weatherApiKey + "&q=" + city;
        return performRequest(url);
    }

    @Override
    public Weather getWeatherByCoordinates(BigDecimal latitude, BigDecimal longitude) {
        String url = WEATHER_URL + weatherApiKey + "&q=" + latitude + "," + longitude;
        return performRequest(url);
    }

    private Weather performRequest(String url) {
        WebClient webClient = WebClient.create(url);

        Mono<String> result = webClient.get()
                .retrieve()
                .bodyToMono(String.class);

        WeatherApiResponse response;
        try {
            response = objectMapper.readValue(result.block(), WeatherApiResponse.class);
            if (response == null) {
                throw new RuntimeException("Weather data not found");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return weatherMapper.toEntity(response);
    }
}
