package com.laoxing.robin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: RobinNz
 * @description:
 * @author: Feri
 * @create: 2020-02-14 11:40
 */
@SpringBootApplication
@EnableDiscoveryClient //发布服务
@EntityScan("com.laoxing.robin.pojo.entity")
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class,args);
    }
}
