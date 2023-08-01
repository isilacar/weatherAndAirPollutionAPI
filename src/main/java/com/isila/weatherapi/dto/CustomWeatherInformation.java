package com.isila.weatherapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomWeatherInformation {

    private String temperature;
    private String tempFeelsLike;
    private String minTemp;
    private String maxTemp;
    private String humidity;
    private String windSpeed;
    private String weatherDetailName;
    private String weatherDescription;
    private String sunrise;
    private String sunset;
    private String informationCreationTime;

}
