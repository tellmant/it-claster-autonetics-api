package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.WeatherMapper;
import com.autonetics.autonetics.api.model.response.WeatherDto;
import com.autonetics.autonetics.api.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService weatherService;
    private final WeatherMapper weatherMapper;

    @GetMapping("/by-city/{city}")
    @Transactional(readOnly = true)
    public ResponseEntity<WeatherDto> getWeatherByCity(@PathVariable String city) {
        WeatherDto response = weatherMapper.toDto(weatherService.getWeatherByCity(city));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-coordinates/{latitude}/{longitude}")
    @Transactional(readOnly = true)
    public ResponseEntity<WeatherDto> getWeatherByCoordinates(@PathVariable BigDecimal latitude, @PathVariable BigDecimal longitude) {
        WeatherDto response = weatherMapper.toDto(weatherService.getWeatherByCoordinates(latitude, longitude));
        return ResponseEntity.ok(response);
    }



}
