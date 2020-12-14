package com.wuzz.study.FeginApi;

import com.wuzz.study.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wuzongzhao
 * @date 2020/12/14 11:53
 */
@FeignClient(value = "order-service",fallback =UserServiceFeign.UserFallback.class )
public interface UserServiceFeign {

    @RequestMapping(value = "/getUsername",method= RequestMethod.GET)
    User getUsername(@RequestParam("username") String username);

    @Component
    class UserFallback implements UserServiceFeign{
        @Override
        public User getUsername(String username) {
            User u=new User();
            u.setUsername("openfenign出错了");
            return u;
        }
    }
}
