package com.isila.weatherapi.request;

import com.isila.weatherapi.enums.TemperatureUnits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentWeatherWithCityNameAndLanguageRequest {

    @NotBlank(message = "have to add city name")
    private String cityName;
    private String lang;
    private TemperatureUnits units;
}
