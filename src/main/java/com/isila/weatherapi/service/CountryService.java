package com.isila.weatherapi.service;

import com.isila.weatherapi.request.CountryRequest;
import com.isila.weatherapi.response.*;

public interface CountryService {
    CountriesResponse getAllCountriesCoordinations();

    CountriesSingleResponse getSpecificCountryCoordination(CountryRequest countryRequest);

    CityResponse getCountriesWithCities();

    CitiesResponse getSpecificCountryCities(CountryRequest countryRequest);

    LanguageCodeListResponse getCountriesLanguageCodes();

    LanguageCodeResponse getSingleCountryLanguageCode(CountryRequest countryRequest);

}
