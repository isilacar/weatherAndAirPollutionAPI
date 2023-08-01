package com.isila.weatherapi.converter;

import com.isila.weatherapi.dto.CustomAirPollutionInformation;
import com.isila.weatherapi.response.AirPollutionResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAirPollutionInformationConverter {


    public CustomAirPollutionInformation getCustomAirPollutionInformation(AirPollutionResponse airPollutionResponse) {

        CustomAirPollutionInformation airPollutionInformation = new CustomAirPollutionInformation();

        airPollutionInformation.setAirQuantity(getAirQuantity(airPollutionResponse.getList().get(0).getMain().getAqi()));

        return airPollutionInformation;
    }

    private String getAirQuantity(Integer index) {
        Map<Integer, String> airQuantityMap = new HashMap<>();
        airQuantityMap.put(1, "Good");
        airQuantityMap.put(2, "Fair");
        airQuantityMap.put(3, "Moderate");
        airQuantityMap.put(4, "Poor");
        airQuantityMap.put(5, "Very Poor");

        return airQuantityMap.get(index);
    }
}
