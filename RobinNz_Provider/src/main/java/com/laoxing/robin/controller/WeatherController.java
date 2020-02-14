package com.laoxing.robin.controller;

import com.laoxing.robin.common.vo.R;
import com.laoxing.robin.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: RobinNz
 * @description:
 * @author: Feri
 * @create: 2020-02-14 15:19
 */
@RestController
public class WeatherController {
    @Autowired
    private WeatherService service;

    //查询今日天气
    @GetMapping("/provider/weather/querytoday.do")
    public R queryToday(@RequestParam String city){
        return service.queryToday(city);
    }
    @GetMapping("/provider/weather/querycity.do")
    public R queryCityAll(@RequestParam String city){
        return service.queryAll(city);
    }
    @GetMapping("/provider/weather/all.do")
    public R all(){
        return service.queryAll();
    }
}
