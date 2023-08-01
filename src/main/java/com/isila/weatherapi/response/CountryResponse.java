package com.isila.weatherapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryResponse {

    private String name;
    @JsonProperty(value = "long")
    private Double lon;
    private Double lat;
}
