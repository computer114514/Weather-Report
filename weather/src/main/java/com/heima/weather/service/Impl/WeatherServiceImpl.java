package com.heima.weather.service.Impl;

import com.heima.weather.domain.pojo.Weather;
import com.heima.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final RestTemplate restTemplate;

    @Value("${weather.baseUrl}")
    private String baseUrl;

    @Value("${weather.key}")
    private String key;
//    https://m77fc2t3hn.re.qweatherapi.com/v7/weather/now?location=101010100&key=7847144afc7d43f8910d9d862712df70
    @Override
    public Weather getRealTimeWeather(String location) {
        String url = String.format("%s?location=%s&key=%s", baseUrl, location, key);
        //获取实时天气
        Weather weather =restTemplate.getForObject(url, Weather.class);
        System.out.println(weather.toString());
        return weather;
    }
}
