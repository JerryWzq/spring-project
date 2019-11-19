package com.wzq.controller;

import com.wzq.constant.PropertiesConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestNacosController {

    @Autowired
    private PropertiesConstant constant;

    @GetMapping("/test")
    public String test(){
        String test = constant.getTest();
        return test;
    }

}
