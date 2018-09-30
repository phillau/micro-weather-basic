package com.liufei.microweatherbasic.service;

import com.liufei.microweatherbasic.vo.WeatherVo;

public interface WeatherService {
    WeatherVo cityName(String cityName);
    WeatherVo cityId(String cityId);
    void syncWeatherData(String url);
}
