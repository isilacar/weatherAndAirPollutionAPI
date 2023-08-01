package com.isila.weatherapi.response;

import com.isila.weatherapi.dto.CustomAirPollutionInformation;
import com.isila.weatherapi.dto.CustomWeatherInformation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class CustomAirPollutionAndWeatherInformationResponse {
    private CustomWeatherInformation weatherInformation;
    private CustomAirPollutionInformation airPollutionInformation;
}
