package com.laoxing.robin.pojo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @program: RobinNz
 * @description:  对应的json
 * @author: Feri
 * @create: 2020-02-14 10:56
 */
@Data
public class WeatherDto {
    private String city;
    private String citycode;
    private Date date;
    private String week;
    private String weather;
    private int temphigh;
    private int templow;
    private String winddirect;
    private String windpower;
}