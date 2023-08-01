package com.isila.weatherapi.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitiesResponse extends BaseResponse {

    List<String> data;
}
