package com.wzq.controller;

import com.wzq.model.User;
import com.wzq.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRegisterController {

    @Autowired
    private UserRegisterService userRegisterService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        //进行注册
        userRegisterService.register(user);
        return "[controller]注册用户成功！";

    }

}
