package com.isila.weatherapi.service;

import com.isila.weatherapi.request.CoordinationRequest;
import com.isila.weatherapi.response.AirPollutionResponse;

public interface AirPollutionService {
    AirPollutionResponse getAirPollution(CoordinationRequest coordinationRequest);

}
