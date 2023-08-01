package com.isila.weatherapi.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class WeatherAirPollutionUtils {

    public String getDateFromUnixSeconds(Long unix) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd.MM.YYYY");
        return formatter.format(new Date(unix * 1000L));
    }

    public Long getWindSpeedFromMeter(Double speed) {
        return Math.round(speed * 3.6);

    }
}
