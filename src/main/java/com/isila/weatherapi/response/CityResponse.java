package com.isila.weatherapi.response;

import com.isila.weatherapi.dto.CountriesWithCities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityResponse extends BaseResponse {

    private List<CountriesWithCities> data;
}
