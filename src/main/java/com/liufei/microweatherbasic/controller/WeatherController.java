package com.liufei.microweatherbasic.controller;

import com.liufei.microweatherbasic.service.WeatherService;
import com.liufei.microweatherbasic.vo.WeatherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/cityName/{cityName}")
    public WeatherVo cityName(@PathVariable("cityName") String cityName){
        return weatherService.cityName(cityName);
    }

    @GetMapping("/cityId/{cityId}")
    public WeatherVo cityId(@PathVariable("cityId") String cityId){
        return weatherService.cityId(cityId);
    }
}
