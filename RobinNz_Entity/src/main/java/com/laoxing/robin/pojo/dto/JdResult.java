package com.laoxing.robin.pojo.dto;

import lombok.Data;

/**
 * @program: RobinNz
 * @description:
 * @author: Feri
 * @create: 2020-02-14 15:13
 */
@Data
public class JdResult {
    private int status;
    private String msg;
    private WeatherDto result;
}
