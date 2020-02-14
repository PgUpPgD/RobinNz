package com.laoxing.robin.pojo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @program: RobinNz
 * @description:
 * @author: Feri
 * @create: 2020-02-14 10:53
 */
@Data
@Entity //标记这个一个映射类
@Table(name = "t_weather") //设置表名
public class Weather {
    @Id //标记主键对应的属性
    @GeneratedValue(strategy = GenerationType.IDENTITY) //设置主键的生成策略 Mysql 自增
    private int id;
    @Column(length = 20,name = "cname")
    private String city;
    private String citycode;
    private Date cdate;
    private String week;
    private String weather;
    private int temphigh;
    private int templow;
    private String winddirect;
    private String windpower;
    private Date ctime;
}