package com.laoxing.robin.common.util;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: RobinNz
 * @description:
 * @author: Feri
 * @create: 2020-02-14 10:58
 */
public class RedissionUtil {
    public static RedissonClient client;
    static {
        Config config=new Config();
        //指定的 类型
        config.useSingleServer().setAddress("redis://39.105.189.141:6379").
                setPassword("qfjava");
        client=Redisson.create(config);
    }
    //新增 删除 修改 查询
    public static void saveStr(String key,String val){
        client.getBucket(key).set(val);
    }
    public static void saveHash(String key,String field,String val){
        client.getMap(key).put(field,val);
    }
    public static void saveHash(String key, Map<String,String> map){
        client.getMap(key).putAll(map);
    }
    public static void saveList(String key,String val){
        client.getList(key).add(val);
    }
    public static void saveHash(String key,String field,String val,int seconds){
        RMap rMap=client.getMap(key);
        rMap.expire(seconds, TimeUnit.SECONDS);
        rMap.put(field,val);
    }
    //设置有效期
    public static void setExpire(String key,int seconds){
        client.getKeys().expire(key,seconds,TimeUnit.SECONDS);
    }
    //查询
    public static String getHashVal(String key,String field){
        return (String) client.getMap(key).get(field);
    }
    //检查指定的字段是否存在
    public static boolean checkHash(String key,String field){
        RMap rMap=client.getMap(key);
        if(rMap!=null){
            return rMap.containsKey(field);
        }else {
            return false;
        }
    }
    //检查指定的Key 存在
    public static boolean checkKey(String key){
        return client.getKeys().countExists(key)>0;
    }
    //删除
    public static void delHash(String key,String field){
        client.getMap(key).remove(field);
    }
    //Redission 分布式锁  基于命令 setnx
    //加锁
    public static RLock lock(String key){
        RLock rLock=client.getLock(key);
        rLock.lock();
        //rLock.lockAsync();
        return rLock;
    }
    //释放锁
    public static void unlock(String key){
        client.getLock(key).unlock();
    }
}