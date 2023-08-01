package com.isila.weatherapi.request;

import com.isila.weatherapi.enums.TemperatureUnits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrentWeatherWithCityNameRequest {

    @NotBlank(message = "have to add city name")
    private String cityName;

    private TemperatureUnits units;
}
