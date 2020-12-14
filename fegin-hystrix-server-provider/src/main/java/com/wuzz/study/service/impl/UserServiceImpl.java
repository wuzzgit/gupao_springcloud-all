package com.wuzz.study.service.impl;

import com.wuzz.study.dto.User;
import com.wuzz.study.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;

/**
 * @author wuzongzhao
 * @date 2020/12/14 11:19
 */
@RestController
public class UserServiceImpl implements IUserService {

    @Override
    public User getUser(String username) {
        System.out.println(">>>>>>>>"+username);
        User user=new User();
        user.setUsername(username);
        return user;
    }

    @Override
    public String getUsername2() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}


