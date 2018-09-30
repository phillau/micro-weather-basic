package com.liufei.microweatherbasic.service.impl;

import com.liufei.microweatherbasic.service.WeatherReportService;
import com.liufei.microweatherbasic.service.WeatherService;
import com.liufei.microweatherbasic.vo.WeatherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherReportServiceImpl implements WeatherReportService {
    @Autowired
    private WeatherService weatherService;
    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini";

    @Override
    public WeatherVo.DataBean weatherReport(String cityId) {
        String url = WEATHER_URI + "?citykey=" + cityId;
        return weatherService.cityId(url).getData();
    }
}
