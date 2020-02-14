package com.laoxing.robin.dao;

import com.laoxing.robin.pojo.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: RobinNz
 * @description:
 * @author: Feri
 * @create: 2020-02-14 11:42
 */
public interface WeatherDao extends JpaRepository<Weather,Integer> {
}