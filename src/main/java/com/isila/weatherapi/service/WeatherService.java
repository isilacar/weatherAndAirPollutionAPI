package com.isila.weatherapi.service;

import com.isila.weatherapi.dto.Coordination;
import com.isila.weatherapi.request.CityRequest;
import com.isila.weatherapi.request.CurrentWeatherRequest;
import com.isila.weatherapi.request.CurrentWeatherWithCityNameAndLanguageRequest;
import com.isila.weatherapi.request.CurrentWeatherWithCityNameRequest;
import com.isila.weatherapi.response.CurrentWeatherResponse;

public interface WeatherService {

    CurrentWeatherResponse currentWeatherWithCoordinations(CurrentWeatherRequest currentWeatherRequest);

    CurrentWeatherResponse currentWeatherWithCityName(CurrentWeatherWithCityNameRequest currentWeatherWithCityNameRequest);

    CurrentWeatherResponse currentWeatherWithCityNameAndLanguage(CurrentWeatherWithCityNameAndLanguageRequest currentWeatherWithCityNameAndLanguageRequest);

    Coordination getCityCoordinations(CityRequest cityRequest);

}
