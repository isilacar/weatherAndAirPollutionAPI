package com.isila.weatherapi.service.impl;

import com.isila.weatherapi.exception.BadRequestException;
import com.isila.weatherapi.exception.InvalidApiKeyException;
import com.isila.weatherapi.request.CoordinationRequest;
import com.isila.weatherapi.response.AirPollutionResponse;
import com.isila.weatherapi.service.AirPollutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AirPollutionServiceImpl implements AirPollutionService {

    private final RestTemplate restTemplate;

    @Value("${weather.appId}")
    private String appid;

    @Value("${weather.base_url}")
    private String baseUrl;

    @Value("${weather.air_pollution_url}")
    private String airPollutionUrl;

    @Override
    public AirPollutionResponse getAirPollution(CoordinationRequest coordinationRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        Map<String, Object> urlParameters = new HashMap<>();
        urlParameters.put("lat", coordinationRequest.getLat());
        urlParameters.put("lon", coordinationRequest.getLon());
        urlParameters.put("appid", appid);
        try {
            return restTemplate.exchange(baseUrl + airPollutionUrl,
                    HttpMethod.GET,
                    requestEntity,
                    AirPollutionResponse.class,
                    urlParameters).getBody();
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new InvalidApiKeyException("Invalid API key");
        } catch (HttpClientErrorException.BadRequest e) {
            String[] message = e.getMessage().split("\"");
            throw new BadRequestException(message[7]);
        }

    }
}
