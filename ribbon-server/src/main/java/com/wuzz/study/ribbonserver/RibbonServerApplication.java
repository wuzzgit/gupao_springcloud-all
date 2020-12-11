package com.wuzz.study.ribbonserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * ribbon服务调用者
 */
@SpringBootApplication
@RibbonClient(name="ribbon-provider-server", configuration=RibbonConfiguration.class)
@EnableEurekaClient
@ComponentScan(basePackages = { "com.wuzz.study"})
public class RibbonServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonServerApplication.class, args);
    }

}
