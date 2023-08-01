package com.isila.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageCode {
    private String name;

    @JsonProperty("Iso2")
    private String languageCode;
}
