package com.isila.weatherapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PopulationCountryCountDto {
    private Long year;
    private Long value;
}
