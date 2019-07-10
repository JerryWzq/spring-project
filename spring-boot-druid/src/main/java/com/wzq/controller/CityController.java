package com.wzq.controller;

import com.wzq.model.City;
import com.wzq.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("城市服务接口")
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService service;

    @ApiOperation(value = "查询城市" ,  notes="获取所有城市信息")
    @GetMapping("/getAll")
    public List<City> getAllData(){
        return service.getAllCityData();
    }


}
