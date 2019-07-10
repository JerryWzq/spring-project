package com.wzq.controller;

import com.wzq.model.City;
import com.wzq.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService service;

    @GetMapping("/getAll")
    public List<City> getAllData(){
        return service.getAllCityData();
    }


}
