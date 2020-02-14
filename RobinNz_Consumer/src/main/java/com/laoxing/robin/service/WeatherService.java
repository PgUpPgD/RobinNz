package com.laoxing.robin.service;

import com.laoxing.robin.common.vo.R;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @program: RobinNz
 * @description:
 * @author: Feri
 * @create: 2020-02-14 16:08
 */
@Service
public class WeatherService {
    @Autowired
    private RestTemplate restTemplate;
    //查询当日
    @HystrixCommand(fallbackMethod = "errorToday")
    public R queryToday(String city){
        return restTemplate.getForObject("http://robinprovider/provider/weather/querytoday.do?city="+city,
                R.class);
    }
    //降级方法 服务出异常的时候，执行本方法
    public R errorToday(String city){
        return R.fail(city+"-天气信息异常");
    }
    public R queryCity(String city){
        return restTemplate.getForObject("http://robinprovider/provider/weather/querycity.do?city="+city,
                R.class);
    }
    public R queryAll(){
        return restTemplate.getForObject("http://robinprovider/provider/weather/all.do",
                R.class);
    }
}
