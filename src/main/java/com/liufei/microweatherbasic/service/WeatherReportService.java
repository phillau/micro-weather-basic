package com.liufei.microweatherbasic.service;

import com.liufei.microweatherbasic.vo.WeatherVo;

public interface WeatherReportService {
    WeatherVo.DataBean weatherReport(String cityId);
}
