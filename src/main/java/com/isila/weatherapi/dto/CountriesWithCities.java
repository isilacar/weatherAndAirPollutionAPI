package com.isila.weatherapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountriesWithCities {

    private String country;
    private List<String> cities;

}
