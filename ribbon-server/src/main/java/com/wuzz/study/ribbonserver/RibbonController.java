package com.wuzz.study.ribbonserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 测试ribbon
 * @author wuzongzhao
 * @date 2020/12/11 16:05
 */
@RestController
@RequestMapping("/ribbon/consumertest")
public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getConsumerTest")
    @AvoidScan
    public String test(){
        String result = restTemplate.getForObject("http://ribbon-provider-server/provider/test/getProviderTest", String.class);
        System.out.println(result);
        return result;
    }

    @GetMapping("/getConsumerTest2")
    public String test2(){
        String result = restTemplate.getForObject("http://ribbon-provider-server/provider/test/getProviderTest2", String.class);
        return result;
    }

}
