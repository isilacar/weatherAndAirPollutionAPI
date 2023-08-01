package com.isila.weatherapi.service.impl;

import com.isila.weatherapi.exception.CountryNotFoundException;
import com.isila.weatherapi.request.CountryRequest;
import com.isila.weatherapi.response.*;
import com.isila.weatherapi.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final RestTemplate restTemplate;

    @Value("${countries.base_url}")
    private String baseUrl;

    @Value("${countries.position_url}")
    private String positionUrl;

    @Value("${countries.specific_country}")
    private String countryUrl;

    @Value("${countries.cities}")
    private String citiesUrl;

    @Value("${countries.language_code}")
    private String languageCodesUrl;


    @Override
    public CountriesSingleResponse getSpecificCountryCoordination(CountryRequest countryRequest) {
        HttpHeaders httpHeaders = getHttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<>(httpHeaders);

        Map<String, Object> urlParameters = new HashMap<>();
        urlParameters.put("country", countryRequest.getCountry());
        try {
            return restTemplate.exchange(baseUrl + positionUrl + countryUrl,
                    HttpMethod.GET,
                    requestEntity,
                    CountriesSingleResponse.class,
                    urlParameters).getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new CountryNotFoundException("Country not found");
        }

    }


    @Override
    public CountriesResponse getAllCountriesCoordinations() {
        HttpHeaders headers = getHttpHeaders();

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<CountriesResponse> responseEntity =
                restTemplate.exchange(baseUrl + positionUrl, HttpMethod.GET, requestEntity, CountriesResponse.class);
        return responseEntity.getBody();
    }

    @Override
    public CityResponse getCountriesWithCities() {

        HttpHeaders headers = getHttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(baseUrl, HttpMethod.GET, requestEntity, CityResponse.class).getBody();

    }

    @Override
    public CitiesResponse getSpecificCountryCities(CountryRequest countryRequest) {
        HttpHeaders headers = getHttpHeaders();

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        Map<String, Object> urlParameters = new HashMap<>();
        urlParameters.put("country", countryRequest.getCountry());

        try {
            return restTemplate.exchange(baseUrl + citiesUrl + countryUrl,
                    HttpMethod.GET, requestEntity, CitiesResponse.class, urlParameters).getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new CountryNotFoundException("Country not found");
        }

    }

    @Override
    public LanguageCodeListResponse getCountriesLanguageCodes() {
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<LanguageCodeListResponse> response =
                restTemplate.exchange(baseUrl + languageCodesUrl, HttpMethod.GET, requestEntity, LanguageCodeListResponse.class);

        return response.getBody();
    }


    @Override
    public LanguageCodeResponse getSingleCountryLanguageCode(CountryRequest countryRequest) {
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        Map<String, Object> urlParameters = new HashMap<>();
        urlParameters.put("country", countryRequest.getCountry());

        try {
            return restTemplate.exchange(baseUrl + languageCodesUrl + countryUrl,
                    HttpMethod.GET,
                    requestEntity,
                    LanguageCodeResponse.class,
                    urlParameters).getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new CountryNotFoundException("Country not found");
        }

    }


    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }

}
