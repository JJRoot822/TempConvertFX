package com.joshrootdev.tempconvert.util;

public class ConversionUtil {
    public static double convertFahrenheitToCelsius(double temperature) {
      return (temperature - 32) * 5 / 9;
    }

    public static double convertCelsiusToFahrenheit(double temperature) {
        return (temperature * 9 / 5) + 32;
    }

    public static double convertFahrenheitToKelvin(double temperature) {
        return (temperature - 32) * 5 / 9 + 273.15;
    }

    public static double convertKelvinToFahrenheit(double temperature) {
        return (temperature - 273.15) * 9 / 5 + 32;
    }

    public static double convertCelsiusToKelvin(double temperature) {
        return temperature + 273.15;
    }

    public static double convertKelvinToCelsius(double temperature) {
        return temperature - 273.16;
    }
}
