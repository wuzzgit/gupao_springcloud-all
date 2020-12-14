package com.wuzz.study.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 测试熔断方式
 * @author wuzongzhao
 * @date 2020/12/14 15:11
 */
@RestController
public class HystixController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/testHystrix")
    @HystrixCommand(commandProperties={
            @HystrixProperty(name="circuitBreaker.enabled",value ="true"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "5"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "5000"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "50")
    },fallbackMethod = "fallback")
    public String testHystrix(){
        return restTemplate.getForObject("http://localhost:8085/getUsername?username=测试",String.class);
    }



    @HystrixCommand(fallbackMethod ="timeoutFallback",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000"),
    })
    @GetMapping("/hystrix/timeout")
    public String queryOrderTimeout(){
        return  restTemplate.getForObject("http://localhost:8085/getUsername2?username=测试",String.class);
    }

    public String fallback(){
        return "熔断异常了";
    }

    public String timeoutFallback(){
        return "超时熔断异常了";
    }
}
