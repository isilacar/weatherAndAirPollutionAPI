package com.isila.weatherapi.enums;


public enum TemperatureUnits {

    METRIC("metric"),
    IMPERIAL("imperial"),
    STANDARD("standard");

    private final String name;

    public String getName() {
        return name;
    }

    TemperatureUnits(String name) {
        this.name = name;
    }
}
