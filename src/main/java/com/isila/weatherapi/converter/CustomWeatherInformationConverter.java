package com.isila.weatherapi.converter;

import com.isila.weatherapi.dto.CustomWeatherInformation;
import com.isila.weatherapi.response.CurrentWeatherResponse;
import com.isila.weatherapi.util.WeatherAirPollutionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomWeatherInformationConverter {


    private final WeatherAirPollutionUtils utils;

    public CustomWeatherInformation getCustomWeatherInformation(CurrentWeatherResponse currentWeatherResponse) {
        CustomWeatherInformation customWeatherInformation = new CustomWeatherInformation();
        customWeatherInformation.setTemperature(Math.round(currentWeatherResponse.getMain().getTemp()) + "°C");
        customWeatherInformation.setTempFeelsLike(Math.round(currentWeatherResponse.getMain().getFeels_like()) + "°C");
        customWeatherInformation.setMinTemp(Math.round(currentWeatherResponse.getMain().getTemp_min()) + "°C");
        customWeatherInformation.setMaxTemp(Math.round(currentWeatherResponse.getMain().getTemp_max()) + "°C");
        customWeatherInformation.setHumidity(currentWeatherResponse.getMain().getHumidity() + "%");
        customWeatherInformation.setWindSpeed(utils.getWindSpeedFromMeter(currentWeatherResponse.getWind().getSpeed()) + "km/h");
        customWeatherInformation.setWeatherDetailName(currentWeatherResponse.getWeather().get(0).getMain());
        customWeatherInformation.setWeatherDescription(currentWeatherResponse.getWeather().get(0).getDescription());
        customWeatherInformation.setSunrise(utils.getDateFromUnixSeconds(currentWeatherResponse.getSys().getSunrise()));
        customWeatherInformation.setSunset(utils.getDateFromUnixSeconds(currentWeatherResponse.getSys().getSunset()));
        customWeatherInformation.setInformationCreationTime(utils.getDateFromUnixSeconds(currentWeatherResponse.getDt()));

        return customWeatherInformation;
    }

}
