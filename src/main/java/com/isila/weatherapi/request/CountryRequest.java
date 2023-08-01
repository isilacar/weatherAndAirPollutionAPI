package com.isila.weatherapi.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryRequest {

    @NotBlank(message = "Country name can not be empty")
    private String country;
}
