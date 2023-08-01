package com.isila.weatherapi.request;

import com.isila.weatherapi.enums.TemperatureUnits;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrentWeatherRequest extends CoordinationRequest {
    private TemperatureUnits units;

}
