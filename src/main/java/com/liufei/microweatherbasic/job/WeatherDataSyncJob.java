package com.liufei.microweatherbasic.job;

import com.liufei.microweatherbasic.service.CityDataService;
import com.liufei.microweatherbasic.service.WeatherService;
import com.liufei.microweatherbasic.service.impl.WeatherServiceImpl;
import com.liufei.microweatherbasic.vo.City;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class WeatherDataSyncJob extends QuartzJobBean {
    Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private CityDataService cityDataService;
    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini";

    @Override
    protected void executeInternal(JobExecutionContext context) {
        try {
            List<City> cityList = cityDataService.listCities();
            for (City city : cityList) {
                String url = WEATHER_URI + "?citykey=" + city.getCityId();
                logger.info("执行定时任务更新天气数据：" + url);
                weatherService.syncWeatherData(url);
            }
        } catch (Exception e) {
            logger.error("Exception", e);
        }
    }
}
