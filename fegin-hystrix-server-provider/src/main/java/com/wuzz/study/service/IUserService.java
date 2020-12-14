package com.wuzz.study.service;

import com.wuzz.study.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wuzongzhao
 * @date 2020/12/14 11:18
 */
public interface IUserService {

    @GetMapping("/getUsername")
    User getUser(@RequestParam("username") String username);

    @GetMapping("/getUsername2")
    String getUsername2();
}
