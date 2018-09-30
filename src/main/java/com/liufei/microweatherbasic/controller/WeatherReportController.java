package com.liufei.microweatherbasic.controller;

import com.liufei.microweatherbasic.service.CityDataService;
import com.liufei.microweatherbasic.service.WeatherReportService;
import com.liufei.microweatherbasic.vo.WeatherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/report")
public class WeatherReportController {
    @Autowired
    private WeatherReportService weatherReportService;
    @Autowired
    private CityDataService cityDataService;

    @GetMapping("/cityId/{cityId}")
    public ModelAndView cityId(@PathVariable("cityId") String cityId) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","刘飞的天气预报");
        modelAndView.addObject("cityId",cityId);
        modelAndView.addObject("cityList",cityDataService.listCities());
        modelAndView.addObject("weatherData",weatherReportService.weatherReport(cityId));
        modelAndView.setViewName("/weather/report");
        return modelAndView;
    }
}
