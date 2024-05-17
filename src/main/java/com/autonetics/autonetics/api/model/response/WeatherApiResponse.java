package com.autonetics.autonetics.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class WeatherApiResponse implements Serializable {

    @JsonProperty("location")
    private Location location;

    @JsonProperty("current")
    private Current current;

    @Getter
    @Setter
    public static class Location {
        @JsonProperty("name")
        private String name;

        @JsonProperty("country")
        private String country;

        @JsonProperty("lat")
        private BigDecimal latitude;

        @JsonProperty("lon")
        private BigDecimal longitude;
    }

    @Getter
    @Setter
    public static class Current {
        @JsonProperty("temp_c")
        private double temperatureCelcius;

        @JsonProperty("feelslike_c")
        private double feelsLikeTemperatureCelcius;

        @JsonProperty("wind_kph")
        private double windSpeed;

        @JsonProperty("humidity")
        private int humidity;

        @JsonProperty("pressure_mb")
        private double pressure;

        @JsonProperty("condition")
        private Condition condition;

        @JsonProperty("is_day")
        private int isDay;

        @Getter
        @Setter
        public static class Condition {
            @JsonProperty("text")
            private String weatherCondition;
        }
    }
}
