package com.isila.weatherapi.service.impl;

import com.isila.weatherapi.converter.CustomAirPollutionInformationConverter;
import com.isila.weatherapi.converter.CustomWeatherInformationConverter;
import com.isila.weatherapi.dto.Coordination;
import com.isila.weatherapi.dto.CustomAirPollutionInformation;
import com.isila.weatherapi.dto.CustomWeatherInformation;
import com.isila.weatherapi.enums.TemperatureUnits;
import com.isila.weatherapi.request.*;
import com.isila.weatherapi.response.*;
import com.isila.weatherapi.service.AirPollutionService;
import com.isila.weatherapi.service.CountryService;
import com.isila.weatherapi.service.CustomAirPollutionAndWeatherInformationService;
import com.isila.weatherapi.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomAirPollutionAndWeatherInformationServiceImpl implements CustomAirPollutionAndWeatherInformationService {

    private final CountryService countryService;
    private final WeatherService weatherService;
    private final AirPollutionService airPollutionService;
    private final CustomWeatherInformationConverter weatherInformationConverter;
    private final CustomAirPollutionInformationConverter airPollutionInformationConverter;

    @Override
    public CustomAirPollutionAndWeatherCountryInformationResponse customAirPollutionAndWeatherInformationByCountryName(CountryRequest countryRequest) {

        CurrentWeatherResponse currentWeatherResponse =
                weatherService.currentWeatherWithCoordinations(getCurrentWeatherRequest(countryRequest.getCountry()));


        CustomWeatherInformation customWeatherInformation =
                weatherInformationConverter.getCustomWeatherInformation(currentWeatherResponse);

        AirPollutionResponse countryAirPollution = airPollutionService.getAirPollution(getCountryCoordinationRequest(countryRequest.getCountry()));
        CustomAirPollutionInformation customAirPollutionInformation =
                airPollutionInformationConverter.getCustomAirPollutionInformation(countryAirPollution);

        CustomAirPollutionAndWeatherCountryInformationResponse airPollutionAndWeatherCountryInformation =
                new CustomAirPollutionAndWeatherCountryInformationResponse();
        airPollutionAndWeatherCountryInformation.setWeatherInformation(customWeatherInformation);
        airPollutionAndWeatherCountryInformation.setAirPollutionInformation(customAirPollutionInformation);
        airPollutionAndWeatherCountryInformation.setCountryName(countryRequest.getCountry());

        return airPollutionAndWeatherCountryInformation;
    }

    @Override
    public CustomAirPollutionAndWeatherCityInformationResponse customAirPollutionAndWeatherInformationByCityName(CityRequest cityRequest) {

        CurrentWeatherResponse currentWeatherResponse = weatherService.currentWeatherWithCityName(getCurrentWeatherWithCityName(cityRequest.getCityName()));

        CustomWeatherInformation customWeatherInformation =
                weatherInformationConverter.getCustomWeatherInformation(currentWeatherResponse);

        Coordination coordination =
                weatherService.getCityCoordinations(getCityCoordinationRequest(cityRequest.getCityName()));

        AirPollutionResponse cityAirPollution = airPollutionService.getAirPollution(new CoordinationRequest(coordination.getLat(),
                coordination.getLon()));

        CustomAirPollutionInformation customAirPollutionInformation =
                airPollutionInformationConverter.getCustomAirPollutionInformation(cityAirPollution);

        CustomAirPollutionAndWeatherCityInformationResponse airPollutionAndWeatherCityInformationResponse =
                new CustomAirPollutionAndWeatherCityInformationResponse();
        airPollutionAndWeatherCityInformationResponse.setWeatherInformation(customWeatherInformation);
        airPollutionAndWeatherCityInformationResponse.setAirPollutionInformation(customAirPollutionInformation);
        airPollutionAndWeatherCityInformationResponse.setCityName(cityRequest.getCityName());

        return airPollutionAndWeatherCityInformationResponse;
    }

    private CityRequest getCityCoordinationRequest(String cityName) {
        CityRequest cityRequest = new CityRequest();
        cityRequest.setCityName(cityName);
        return cityRequest;
    }

    private CurrentWeatherRequest getCurrentWeatherRequest(String countryName) {
        CountryResponse countryPosition = getCountryPosition(countryName);

        CurrentWeatherRequest currentWeatherRequest = new CurrentWeatherRequest();
        currentWeatherRequest.setUnits(TemperatureUnits.METRIC);
        currentWeatherRequest.setLat(countryPosition.getLat());
        currentWeatherRequest.setLon(countryPosition.getLon());

        return currentWeatherRequest;
    }

    private CoordinationRequest getCountryCoordinationRequest(String countryName) {
        CountryResponse countryPosition = getCountryPosition(countryName);

        CoordinationRequest coordinationRequest = new CoordinationRequest();
        coordinationRequest.setLat(countryPosition.getLat());
        coordinationRequest.setLon(countryPosition.getLon());

        return coordinationRequest;
    }

    private CountryResponse getCountryPosition(String countryName) {
        CountriesSingleResponse countryCoordination =
                countryService.getSpecificCountryCoordination(new CountryRequest(countryName));

        return new CountryResponse(countryName,
                countryCoordination.getData().getLon(),
                countryCoordination.getData().getLat());
    }

    private CurrentWeatherWithCityNameRequest getCurrentWeatherWithCityName(String cityName) {
        CurrentWeatherWithCityNameRequest currentWeatherWithCityNameRequest = new CurrentWeatherWithCityNameRequest();
        currentWeatherWithCityNameRequest.setCityName(cityName);
        currentWeatherWithCityNameRequest.setUnits(TemperatureUnits.METRIC);

        return currentWeatherWithCityNameRequest;
    }
}
