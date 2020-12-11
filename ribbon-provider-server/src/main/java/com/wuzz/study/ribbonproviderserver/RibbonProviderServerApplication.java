package com.wuzz.study.ribbonproviderserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * ribbon服务提供者
 */
@SpringBootApplication
@EnableEurekaClient
public class RibbonProviderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonProviderServerApplication.class, args);
    }

}
