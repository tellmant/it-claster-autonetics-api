package com.autonetics.autonetics.api.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class Weather {
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String city;
    private String country;
    private double temperatureCelcius;
    private double feelsLikeTemperatureCelcius;
    private double windSpeed;
    private int humidity;
    private double pressure;
    private String weatherCondition;
    private int isDay;
}
