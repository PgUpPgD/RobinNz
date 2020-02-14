package com.laoxing.robin.config;

import com.netflix.loadbalancer.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @program: RobinNz
 * @description:
 * @author: Feri
 * @create: 2020-02-14 16:00
 */
@Configuration
public class RibbonConfig {

    @Bean
    @LoadBalanced //启用负载均衡
    public RestTemplate cresteRT() {
        return new RestTemplate();
    }
    //负载均衡算法
    @Bean
    public IRule createRule(){
        //随机
        RandomRule rule1=new RandomRule();//
        //轮询  默认
        RoundRobinRule rule2=new RoundRobinRule();
        //区域感知
        ZoneAvoidanceRule rule3=new ZoneAvoidanceRule();
        //并发最小
        BestAvailableRule rule4=new BestAvailableRule();
        //权重
        WeightedResponseTimeRule rule5=new WeightedResponseTimeRule();
        return rule1;
    }
}
