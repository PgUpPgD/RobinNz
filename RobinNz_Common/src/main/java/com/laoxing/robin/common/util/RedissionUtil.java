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
        config.useSingleServer().setAddress("39.105.189.141:6379").
                setPassword("qfjava");
        RedissonClient client=Redisson.create(config);
    }
    //新增 删除 修改 查询
    public void saveStr(String key,String val){
        client.getBucket(key).set(val);
    }
    public void saveHash(String key,String field,String val){
        client.getMap(key).put(field,val);
    }
    public void saveHash(String key, Map<String,String> map){
        client.getMap(key).putAll(map);
    }
    public void saveList(String key,String val){
        client.getList(key).add(val);
    }
    public void saveHash(String key,String field,String val,int seconds){
        RMap rMap=client.getMap(key);
        rMap.expire(seconds, TimeUnit.SECONDS);
        rMap.put(field,val);
    }
    //设置有效期
    public void setExpire(String key,int seconds){
        client.getKeys().expire(key,seconds,TimeUnit.SECONDS);
    }
    //查询
    public String getHashVal(String key,String field){
        return (String) client.getMap(key).get(field);
    }
    //删除
    public void delHash(String key,String field){
        client.getMap(key).remove(field);
    }
    //Redission 分布式锁  基于命令 setnx
    //加锁
    public RLock lock(String key){
        RLock rLock=client.getLock(key);
        rLock.lock();
        //rLock.lockAsync();
        return rLock;
    }
    //释放锁
    public void unlock(String key){
        client.getLock(key).unlock();
    }




}
