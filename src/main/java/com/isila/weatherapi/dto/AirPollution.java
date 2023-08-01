package com.isila.weatherapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirPollution {
    private Long dt;
    private AirPollutionMain main;
    private AirPollutionComponents components;
}
