package com.isila.weatherapi.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinationRequest {

    private Double lat;
    private Double lon;

}
