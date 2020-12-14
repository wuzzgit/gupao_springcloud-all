package com.wuzz.study.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wuzz.study.custom.CustomHystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 自定义超时熔断注解
 * @author wuzongzhao
 * @date 2020/12/14 16:37
 */
@RestController
public class CustomHystrixController {


    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/testCustomHystrix")
    @CustomHystrixCommand(fallback = "fallback", timeout = 3000)
    public String testHystrix(){
        return  restTemplate.getForObject("http://localhost:8085/getUsername2?username=测试",String.class);
    }

    public String fallback(){
        return "Custom超时熔断异常了";
    }
}
