package com.wzq.spring.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/echo")
    public String echo(){
        return "service start....";
    }

}
