package com.wuzz.study.ribbonproviderserver;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wuzongzhao
 * @date 2020/12/11 16:18
 */
@RestController
@RequestMapping("/provider/test")
public class RibbonProviderServerController {

    @RequestMapping(value="/getProviderTest", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public String test(HttpServletRequest request){
        return request.getRequestURL().toString();
    }

    @GetMapping("/getProviderTest2")
    public String test2(){
        return "test server";
    }

}
