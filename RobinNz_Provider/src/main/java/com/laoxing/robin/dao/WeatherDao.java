package com.laoxing.robin.dao;

import com.laoxing.robin.pojo.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @program: RobinNz
 * @description:
 * @author: Feri
 * @create: 2020-02-14 11:42
 */
public interface WeatherDao extends JpaRepository<Weather,Integer> {
    //方法名解析查询
    Weather getByCityAndCdate(String city, Date cdate);
    //JPQL语言查询  获取参数的方式：?序号 从1开始 指定名称获取 :名称
    @Query(value = "from Weather where city=?1")
    List<Weather> queryCity(@Param("nnn") String city);
    //原生SQL查询
//    @Query(value = "select * from t_weather where city=#{city}",nativeQuery = true)
//    List<Weather> queryCity(String city);
}