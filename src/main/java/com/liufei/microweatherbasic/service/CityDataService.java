package com.liufei.microweatherbasic.service;

import com.liufei.microweatherbasic.vo.City;
import java.util.List;

public interface CityDataService {
    List<City> listCities() throws Exception;
}
