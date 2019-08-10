package com.wzq.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzq.model.City;
import com.wzq.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("城市服务接口")
@RestController
@RequestMapping("/city")
public class CityController {

    private static final Logger logger = LoggerFactory.getLogger(CityController.class);

    @Autowired
    private CityService service;

    @ApiOperation(value = "查询城市" ,  notes="获取所有城市信息")
    @GetMapping("/getAll")
    public Object getAllData(){
        PageHelper.startPage(1, 10);
        List<City> cities = service.getAllCityData();
        PageInfo<City> pageInfo = new PageInfo<>(cities);
        return pageInfo;
    }

    @GetMapping("/hello")
    public Object hello(@RequestParam String name){
        logger.info("hello request: {}", name);
        return name;
    }

    @PostMapping("/{id}")
    public Object getCityById(@PathVariable  Integer id){
        logger.info("get city info, id: {}", id);
        return service.getCityById(id);
    }

    @PostMapping("/insert")
    public Object insert(@RequestBody City city){
        logger.info("insert city: {}", city);
        return service.insert(city);
    }

    @GetMapping("/echo")
    public Object echo(){
        return "echo request";
    }

}
