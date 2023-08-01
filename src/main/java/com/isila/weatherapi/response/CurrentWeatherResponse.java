package com.isila.weatherapi.response;

import com.isila.weatherapi.dto.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentWeatherResponse {

    private Coordination coord;
    private List<Weather> weather;
    private String base;
    private MainValues main;
    private Integer visibility;
    private Wind wind;
    private Clouds clouds;
    private Long dt;
    private Sys sys;
    private Integer timezone;
    private Long id;
    private String name;
    private Integer cod;

}
