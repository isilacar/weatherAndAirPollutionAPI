package com.isila.weatherapi.controller;

import com.isila.weatherapi.request.CoordinationRequest;
import com.isila.weatherapi.response.AirPollutionResponse;
import com.isila.weatherapi.service.AirPollutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airPollution")
public class AirPollutionController {

    private final AirPollutionService airPollutionService;

    @PostMapping
    public ResponseEntity<AirPollutionResponse> getAirPollution(@RequestBody CoordinationRequest coordinationRequest) {
        return new ResponseEntity<>(airPollutionService.getAirPollution(coordinationRequest), HttpStatus.OK);
    }
}
