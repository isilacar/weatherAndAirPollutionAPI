package com.isila.weatherapi.controller;

import com.isila.weatherapi.request.CountryRequest;
import com.isila.weatherapi.response.*;
import com.isila.weatherapi.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/countries")
public class CountryAndCityController {

    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<CountriesResponse> getAllCountries() {
        return new ResponseEntity<>(countryService.getAllCountriesCoordinations(), HttpStatus.OK);
    }

    @PostMapping("/country")
    public ResponseEntity<CountriesSingleResponse> getCountryCoordination(@Valid @RequestBody CountryRequest countryRequest) {
        return new ResponseEntity<>(countryService.getSpecificCountryCoordination(countryRequest), HttpStatus.OK);
    }

    @GetMapping("/cities")
    public ResponseEntity<CityResponse> getCountriesWithCities() {
        return new ResponseEntity<>(countryService.getCountriesWithCities(), HttpStatus.OK);
    }

    @PostMapping("/country/cities")
    public ResponseEntity<CitiesResponse> getSpecificCountryCities(@Valid @RequestBody CountryRequest countryRequest) {
        return new ResponseEntity<>(countryService.getSpecificCountryCities(countryRequest), HttpStatus.OK);
    }

    @GetMapping("/iso")
    public ResponseEntity<LanguageCodeListResponse> getAllLanguageCodes() {
        return new ResponseEntity<>(countryService.getCountriesLanguageCodes(), HttpStatus.OK);
    }

    @PostMapping("/country/iso")
    public ResponseEntity<LanguageCodeResponse> getSingleCountryLanguageCode(@Valid @RequestBody CountryRequest countryRequest) {
        return new ResponseEntity<>(countryService.getSingleCountryLanguageCode(countryRequest), HttpStatus.OK);
    }


}
