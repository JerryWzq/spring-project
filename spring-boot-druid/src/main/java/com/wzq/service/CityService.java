package com.wzq.service;

import com.wzq.mapper.CityMapper;
import com.wzq.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityMapper cityMapper;

    public List<City> getAllCityData(){
        return cityMapper.getAllCity();
    }

    public City getCityById(Integer id){
        return cityMapper.getCityById(id);
    }

    public Integer insert(City city){
        return cityMapper.insert(city);
    }
}
