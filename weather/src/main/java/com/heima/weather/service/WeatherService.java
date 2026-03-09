package com.heima.weather.service;

import com.heima.weather.domain.pojo.Weather;

public interface WeatherService {
    Weather getRealTimeWeather(String location);

}
