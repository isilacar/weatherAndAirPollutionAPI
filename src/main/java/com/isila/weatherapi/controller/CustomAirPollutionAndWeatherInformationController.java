package com.isila.weatherapi.controller;

import com.isila.weatherapi.request.CityRequest;
import com.isila.weatherapi.request.CountryRequest;
import com.isila.weatherapi.response.CustomAirPollutionAndWeatherCountryInformationResponse;
import com.isila.weatherapi.response.CustomAirPollutionAndWeatherInformationResponse;
import com.isila.weatherapi.service.CustomAirPollutionAndWeatherInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomAirPollutionAndWeatherInformationController {

    private final CustomAirPollutionAndWeatherInformationService customAirPollutionAndWeatherInformationService;

    @PostMapping("/countryName")
    public ResponseEntity<CustomAirPollutionAndWeatherCountryInformationResponse> getCountryInformation(@Valid @RequestBody CountryRequest countryRequest) {
        return new ResponseEntity<>(customAirPollutionAndWeatherInformationService.customAirPollutionAndWeatherInformationByCountryName(countryRequest),
                HttpStatus.OK);
    }

    @PostMapping("/cityName")
    public ResponseEntity<CustomAirPollutionAndWeatherInformationResponse> getCityInformation(@Valid @RequestBody CityRequest cityRequest) {
        return new ResponseEntity<>(customAirPollutionAndWeatherInformationService.customAirPollutionAndWeatherInformationByCityName(cityRequest),
                HttpStatus.OK);
    }
}
