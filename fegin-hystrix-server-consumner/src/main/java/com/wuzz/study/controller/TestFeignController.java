package com.wuzz.study.controller;

import com.wuzz.study.FeginApi.UserServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * feign调用测试
 * @author wuzongzhao
 * @date 2020/12/14 11:56
 */
@RestController
public class TestFeignController {

    @Autowired
    private UserServiceFeign userServiceFeign;

    @GetMapping("/getUser")
    public String getUsername(String username){
        return userServiceFeign.getUsername(username).getUsername();
    }

}
