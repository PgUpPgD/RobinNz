package com.laoxing.robin.common.config;

/**
 * @program: RobinNz
 * @description:
 * @author: Feri
 * @create: 2020-02-14 14:39
 */
public class RedisKeyConfig {

    //记录天气查询的Hash类型的Key 做热点数据 有效期：当日有效
    //字段：城市名称 值：对应城市的当天的天气信息 json 格式 Weather
    public static final String KEY_WEATHER_TODAY="robin:weather";


}
