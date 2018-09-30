package com.liufei.microweatherbasic.service.impl;

import com.google.gson.Gson;
import com.liufei.microweatherbasic.service.WeatherService;
import com.liufei.microweatherbasic.vo.WeatherVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.TimeUnit;

@Service
public class WeatherServiceImpl implements WeatherService {
    Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini";
    private static final Gson gson = new Gson();
    private static final long TIME = 30L;

    @Override
    public WeatherVo cityName(String cityName) {
        String url = WEATHER_URI + "?city=" + cityName;
        return getWeatherVo(url);
    }

    @Override
    public WeatherVo cityId(String cityId) {
        String url = WEATHER_URI + "?citykey=" + cityId;
        return getWeatherVo(url);
    }

    @Override
    public void syncWeatherData(String url) {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        opsForValue.set(url, forEntity.getBody(), TIME, TimeUnit.MINUTES);
    }

    private WeatherVo getWeatherVo(String url) {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        //先从redis缓存中取，如果有就直接返回，没有就调用接口返回并存入redis设置超时时间为30分钟
        if (stringRedisTemplate.hasKey(url)) {
            logger.info("redis中有数据");
            return gson.fromJson(opsForValue.get(url), WeatherVo.class);
        } else {
            logger.info("redis中无数据");
            ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
            opsForValue.set(url, forEntity.getBody(), TIME, TimeUnit.MINUTES);
            return gson.fromJson(forEntity.getBody(), WeatherVo.class);
        }
    }
}