package com.isila.weatherapi.response;


import com.isila.weatherapi.dto.AirPollution;
import com.isila.weatherapi.dto.Coordination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirPollutionResponse {

    private Coordination coord;
    private List<AirPollution> list;

}
