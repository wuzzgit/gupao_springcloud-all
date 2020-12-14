package com.wuzz.study.controller;

import com.wuzz.study.FeginApi.UserServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试openFeign-熔断机制
 * @author wuzongzhao
 * @date 2020/12/14 15:43
 */
@RestController
public class OpenFeignController {

    @Autowired
    private UserServiceFeign userServiceFeign;

    @GetMapping("/getOpenFeignUser")
    public String getUsername(String username){
        return userServiceFeign.getUsername(username).getUsername();
    }
}
