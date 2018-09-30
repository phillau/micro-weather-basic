package com.liufei.microweatherbasic.service.impl;

import com.liufei.microweatherbasic.service.CityDataService;
import com.liufei.microweatherbasic.util.XmlBuilder;
import com.liufei.microweatherbasic.vo.City;
import com.liufei.microweatherbasic.vo.CityList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class CityDataServiceImpl implements CityDataService {
    @Override
    public List<City> listCities() throws Exception {
        // 读取XML文件
        ClassPathResource resource = new ClassPathResource("citylist.xml");
        InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream(), "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        while ((line=bufferedReader.readLine())!=null){
            stringBuffer.append(line);
        }
        bufferedReader.close();
        // XML转为Java对象
        CityList cityList = (CityList) XmlBuilder.xmlStrToOject(CityList.class, stringBuffer.toString());
        return cityList.getCityList();
    }
}
