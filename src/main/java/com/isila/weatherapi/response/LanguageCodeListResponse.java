package com.isila.weatherapi.response;

import com.isila.weatherapi.dto.LanguageCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageCodeListResponse extends BaseResponse {
    private List<LanguageCode> data;
}
