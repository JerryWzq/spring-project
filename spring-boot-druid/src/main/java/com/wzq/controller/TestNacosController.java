package com.wzq.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@NacosPropertySource(dataId = "spring-boot2", autoRefreshed = true)
@RestController
public class TestNacosController {

    @NacosValue(value = "${nacos.test.propertie:123}", autoRefreshed = true)
    private String testProperties;

    @GetMapping("/test")
    public String test(){
        return testProperties;
    }

}
