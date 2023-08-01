package com.isila.weatherapi.response;

import com.isila.weatherapi.dto.LanguageCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageCodeResponse extends BaseResponse {

    private LanguageCode data;
}
