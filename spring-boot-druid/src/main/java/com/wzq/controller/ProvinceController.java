package com.wzq.controller;

import com.wzq.model.City;
import com.wzq.model.Province;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("省份服务接口")
@RestController
@RequestMapping("/province")
public class ProvinceController {

    @ApiOperation(value = "保存省份信息" ,  notes="保存省份信息")
    @PostMapping("/save")
    public String saveProvince(@RequestBody Province<City> province){
        System.err.println(province);
        return province.toString();
    }

}
