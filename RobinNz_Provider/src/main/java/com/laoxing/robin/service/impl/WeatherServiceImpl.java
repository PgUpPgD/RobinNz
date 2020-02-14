package com.laoxing.robin.service.impl;

import com.alibaba.fastjson.JSON;
import com.laoxing.robin.common.config.RedisKeyConfig;
import com.laoxing.robin.common.config.SystemConfig;
import com.laoxing.robin.common.util.DateUtil;
import com.laoxing.robin.common.util.HttpUtil;
import com.laoxing.robin.common.util.RedissionUtil;
import com.laoxing.robin.common.vo.R;
import com.laoxing.robin.dao.WeatherDao;
import com.laoxing.robin.pojo.dto.JdWeatherDto;
import com.laoxing.robin.pojo.dto.WeatherDto;
import com.laoxing.robin.pojo.entity.Weather;
import com.laoxing.robin.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: RobinNz
 * @description:
 * @author: Feri
 * @create: 2020-02-14 14:36
 */
@Service
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    private WeatherDao weatherDao;
    //查询今日天气
    @Override
    public R queryToday(String city) {
        //1.检索Redis 是否存在 数据
        if(RedissionUtil.checkHash(RedisKeyConfig.KEY_WEATHER_TODAY,city)){
            //存在就获取数据
            String str=RedissionUtil.getHashVal(RedisKeyConfig.KEY_WEATHER_TODAY,city);
            //转换为对象 返回
            return R.ok(JSON.parseObject(str, Weather.class));
        }else {//Redis没有数据---
            //2.查询数据库
            Weather weather=weatherDao.getByCityAndCdate(city, new Date());
            if(weather!=null){
                //这个城市的 今日的天气信息  存在
                //同步数据到Redis 需要校验是不是第一次 第一次设置有效期
                if(!RedissionUtil.checkKey(RedisKeyConfig.KEY_WEATHER_TODAY)){
                    //创建并设置有效期  当日有效  24-now
                    RedissionUtil.saveHash(RedisKeyConfig.KEY_WEATHER_TODAY,
                            city, JSON.toJSONString(weather),DateUtil.getSeconds());
                }else {
                    RedissionUtil.saveHash(RedisKeyConfig.KEY_WEATHER_TODAY, city, JSON.toJSONString(weather));
                }
                return R.ok(weather);
            }else {
                //数据库不存在
                //请求网络接口
                String json= HttpUtil.getJson(SystemConfig.WEATHER_URL+city);
                //校验是否获取结果
                if(json!=null){
                    //解析结果
                    JdWeatherDto dto=JSON.parseObject(json,JdWeatherDto.class);
                    if(dto.getCode()==10000&& dto.getResult().getStatus()==0 ){
                        WeatherDto weatherDto=dto.getResult().getResult();
                        //同步数据到Mysql --->Redis
                        Weather weather1=new Weather();
                        weather1.setCdate(weatherDto.getDate());
                        weather1.setCity(weatherDto.getCity());
                        weather1.setCitycode(weatherDto.getCitycode());
                        weather1.setTemphigh(weatherDto.getTemphigh());
                        weather1.setTemplow(weatherDto.getTemplow());
                        weather1.setWeather(weatherDto.getWeather());
                        weather1.setWeek(weatherDto.getWeek());
                        weather1.setWinddirect(weatherDto.getWinddirect());
                        weather1.setWindpower(weatherDto.getWindpower());
                        weather1.setCtime(new Date());
                        //存储到Mysql
                        weatherDao.save(weather1);
                        //再将数据存储到Redis
                        //这个城市的 今日的天气信息  存在
                        //同步数据到Redis 需要校验是不是第一次 第一次设置有效期
                        if(!RedissionUtil.checkKey(RedisKeyConfig.KEY_WEATHER_TODAY)){
                            //创建并设置有效期  当日有效  24-now
                            RedissionUtil.saveHash(RedisKeyConfig.KEY_WEATHER_TODAY,
                                    city, JSON.toJSONString(weather1),DateUtil.getSeconds());
                        }else {
                            RedissionUtil.saveHash(RedisKeyConfig.KEY_WEATHER_TODAY, city, JSON.toJSONString(weather1));
                        }
                        return R.ok(weather1);
                    }
                }
            }
        }
        return R.fail("城市异常");
    }

    @Override
    public R queryAll(String city) {
        //查询数据库
        return R.ok(weatherDao.queryCity(city));
    }

    @Override
    public R queryAll() {
        return R.ok(weatherDao.findAll());
    }
}
