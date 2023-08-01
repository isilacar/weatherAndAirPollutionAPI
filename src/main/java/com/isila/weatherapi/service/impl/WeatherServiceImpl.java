package com.isila.weatherapi.service.impl;

import com.isila.weatherapi.dto.Coordination;
import com.isila.weatherapi.enums.TemperatureUnits;
import com.isila.weatherapi.exception.*;
import com.isila.weatherapi.request.CityRequest;
import com.isila.weatherapi.request.CurrentWeatherRequest;
import com.isila.weatherapi.request.CurrentWeatherWithCityNameAndLanguageRequest;
import com.isila.weatherapi.request.CurrentWeatherWithCityNameRequest;
import com.isila.weatherapi.response.CurrentWeatherResponse;
import com.isila.weatherapi.service.WeatherService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final RestTemplate restTemplate;

    @Value("${weather.appId}")
    private String appid;

    @Value("${weather.base_url}")
    private String baseUrl;

    @Value("${weather.current_coords_url}")
    private String currentWeatherCoordsUrl;

    @Value("${weather.current_city_name_url}")
    private String currentWeatherCityNameUrl;

    @Value("${weather.current_city_name_language_url}")
    private String currentWeatherCityNameCustomLangUrl;


    @Value("${weather.coordinations_from_city_name}")
    private String cityCoordination;

    @Override
    public CurrentWeatherResponse currentWeatherWithCoordinations(CurrentWeatherRequest currentWeatherRequest) {
        HttpEntity<Object> requestEntity = getHttpEntity();

        Map<String, Object> urlParameters = new HashMap<>();
        urlParameters.put("lat", currentWeatherRequest.getLat());
        urlParameters.put("lon", currentWeatherRequest.getLon());
        urlParameters.put("appid", appid);
        if (Objects.isNull(currentWeatherRequest.getUnits())) {
            currentWeatherRequest.setUnits(TemperatureUnits.STANDARD);
        }
        urlParameters.put("units", currentWeatherRequest.getUnits().getName());

        ResponseEntity<CurrentWeatherResponse> responseEntity;
        try {
            responseEntity = restTemplate.exchange(
                    baseUrl + currentWeatherCoordsUrl,
                    HttpMethod.GET,
                    requestEntity,
                    CurrentWeatherResponse.class,
                    urlParameters);
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new InvalidApiKeyException("Invalid API key");
        } catch (HttpClientErrorException.NotFound e) {
            throw new CountryNotFoundException("Country Not Found");
        } catch (HttpClientErrorException.BadRequest e) {
            String[] message = e.getMessage().split("\"");
            throw new BadRequestException(message[7]);
        }
        return responseEntity.getBody();

    }

    @Override
    public CurrentWeatherResponse currentWeatherWithCityName(CurrentWeatherWithCityNameRequest currentWeatherWithCityNameRequest) {
        HttpEntity<Object> requestEntity = getHttpEntity();

        Map<String, Object> urlParameters = new HashMap<>();
        urlParameters.put("appid", appid);
        urlParameters.put("cityName", currentWeatherWithCityNameRequest.getCityName());
        if (Objects.isNull(currentWeatherWithCityNameRequest.getUnits())) {
            currentWeatherWithCityNameRequest.setUnits(TemperatureUnits.STANDARD);
        }
        urlParameters.put("units", currentWeatherWithCityNameRequest.getUnits().getName());

        ResponseEntity<CurrentWeatherResponse> responseEntity;
        try {
            responseEntity = restTemplate.exchange(
                    baseUrl + currentWeatherCityNameUrl,
                    HttpMethod.GET,
                    requestEntity,
                    CurrentWeatherResponse.class,
                    urlParameters);
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException("City Not Found");
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new InvalidApiKeyException("Invalid API key");
        }
        return responseEntity.getBody();
    }

    @Override
    public CurrentWeatherResponse currentWeatherWithCityNameAndLanguage(CurrentWeatherWithCityNameAndLanguageRequest currentWeatherWithCityNameAndLanguageRequest) {
        HttpEntity<Object> requestEntity = getHttpEntity();

        Map<String, Object> urlParameters = new HashMap<>();
        urlParameters.put("appid", appid);
        urlParameters.put("cityName", currentWeatherWithCityNameAndLanguageRequest.getCityName());
        if (Objects.isNull(currentWeatherWithCityNameAndLanguageRequest.getUnits())) {
            currentWeatherWithCityNameAndLanguageRequest.setUnits(TemperatureUnits.STANDARD);
        }
        urlParameters.put("units", currentWeatherWithCityNameAndLanguageRequest.getUnits().getName());
        urlParameters.put("lang", currentWeatherWithCityNameAndLanguageRequest.getLang());

        ResponseEntity<CurrentWeatherResponse> responseEntity;
        try {
            responseEntity = restTemplate.exchange(
                    baseUrl + currentWeatherCityNameCustomLangUrl,
                    HttpMethod.GET,
                    requestEntity,
                    CurrentWeatherResponse.class,
                    urlParameters);
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException("City Not Found");
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new InvalidApiKeyException("Invalid API key");
        }

        return responseEntity.getBody();
    }

    @Override
    public Coordination getCityCoordinations(CityRequest cityRequest) {
        HttpEntity<Object> requestEntity = getHttpEntity();
        Map<String, Object> urlParameters = new HashMap<>();
        urlParameters.put("cityName", cityRequest.getCityName());
        urlParameters.put("appid", appid);

        ResponseEntity<List<Coordination>> responseEntity = restTemplate.exchange(baseUrl + cityCoordination,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                },
                urlParameters);
        if (responseEntity.getBody().isEmpty()) {
            throw new CityNotFoundException("City Not Found");
        }

        return getCityCoordinations(responseEntity);

    }


    private static HttpEntity<Object> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        return requestEntity;
    }

    private Coordination getCityCoordinations(ResponseEntity<List<Coordination>> coordinations) {
        Coordination cityCoordiations = coordinations.getBody().stream().findFirst().get();
        cityCoordiations.setLon((double) Math.round(cityCoordiations.getLon()));
        cityCoordiations.setLat((double) Math.round(cityCoordiations.getLat()));
        return cityCoordiations;
    }
}
