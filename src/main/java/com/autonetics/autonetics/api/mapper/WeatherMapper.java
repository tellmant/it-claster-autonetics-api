package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.Weather;
import com.autonetics.autonetics.api.model.response.WeatherApiResponse;
import com.autonetics.autonetics.api.model.response.WeatherDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface WeatherMapper {
    Weather toEntity(WeatherDto weatherDto);
    WeatherDto toDto(Weather weather);

    @Mappings({
            @Mapping(source = "location.name", target = "city"),
            @Mapping(source = "location.country", target = "country"),
            @Mapping(source = "location.latitude", target = "latitude"),
            @Mapping(source = "location.longitude", target = "longitude"),
            @Mapping(source = "current.temperatureCelcius", target = "temperatureCelcius"),
            @Mapping(source = "current.feelsLikeTemperatureCelcius", target = "feelsLikeTemperatureCelcius"),
            @Mapping(source = "current.windSpeed", target = "windSpeed"),
            @Mapping(source = "current.humidity", target = "humidity"),
            @Mapping(source = "current.pressure", target = "pressure"),
            @Mapping(source = "current.condition.weatherCondition", target = "weatherCondition"),
            @Mapping(source = "current.isDay", target = "isDay")
    })
    WeatherDto toDto(WeatherApiResponse weatherApiResponse);

    @Mappings({
            @Mapping(source = "location.name", target = "city"),
            @Mapping(source = "location.country", target = "country"),
            @Mapping(source = "location.latitude", target = "latitude"),
            @Mapping(source = "location.longitude", target = "longitude"),
            @Mapping(source = "current.temperatureCelcius", target = "temperatureCelcius"),
            @Mapping(source = "current.feelsLikeTemperatureCelcius", target = "feelsLikeTemperatureCelcius"),
            @Mapping(source = "current.windSpeed", target = "windSpeed"),
            @Mapping(source = "current.humidity", target = "humidity"),
            @Mapping(source = "current.pressure", target = "pressure"),
            @Mapping(source = "current.condition.weatherCondition", target = "weatherCondition"),
            @Mapping(source = "current.isDay", target = "isDay")
    })
    Weather toEntity(WeatherApiResponse weatherApiResponse);

}