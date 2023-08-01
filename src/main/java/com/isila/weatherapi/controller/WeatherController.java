package com.isila.weatherapi.controller;

import com.isila.weatherapi.dto.Coordination;
import com.isila.weatherapi.request.CityRequest;
import com.isila.weatherapi.request.CurrentWeatherRequest;
import com.isila.weatherapi.request.CurrentWeatherWithCityNameAndLanguageRequest;
import com.isila.weatherapi.request.CurrentWeatherWithCityNameRequest;
import com.isila.weatherapi.response.CurrentWeatherResponse;
import com.isila.weatherapi.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @PostMapping
    public ResponseEntity<CurrentWeatherResponse> getCurrentWeatherWithCoordinations(@RequestBody CurrentWeatherRequest currentWeatherRequest) {
        return new ResponseEntity<>(weatherService.currentWeatherWithCoordinations(currentWeatherRequest), HttpStatus.OK);
    }

    @PostMapping("/cityname")
    public ResponseEntity<CurrentWeatherResponse> getCurrentWeatherWithCityName(@Valid @RequestBody CurrentWeatherWithCityNameRequest currentWeatherWithCityNameRequest) {
        return new ResponseEntity<>(weatherService.currentWeatherWithCityName(currentWeatherWithCityNameRequest), HttpStatus.OK);
    }

    @PostMapping("/cityname/lang")
    public ResponseEntity<CurrentWeatherResponse> getCurrentWeatherWithCityNameAndLanguage(@Valid @RequestBody CurrentWeatherWithCityNameAndLanguageRequest currentWeatherWithCityNameAndLanguageRequest) {
        return new ResponseEntity<>(weatherService.currentWeatherWithCityNameAndLanguage(currentWeatherWithCityNameAndLanguageRequest), HttpStatus.OK);
    }

    @PostMapping("/city")
    public ResponseEntity<Coordination> getCoordinations(@Valid @RequestBody CityRequest cityRequest) {
        return new ResponseEntity<>(weatherService.getCityCoordinations(cityRequest), HttpStatus.OK);
    }

}
