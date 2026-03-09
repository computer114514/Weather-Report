package com.heima.weather.controller;

import com.heima.weather.domain.pojo.Result;
import com.heima.weather.domain.pojo.Weather;
import com.heima.weather.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class weatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public Result<Weather>  getRealTimeWeather(String location){
        Weather weather =weatherService.getRealTimeWeather(location);
        return Result.success(weather);
    }
}
