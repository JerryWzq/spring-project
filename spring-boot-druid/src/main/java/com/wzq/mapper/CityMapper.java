package com.wzq.mapper;

import com.wzq.model.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CityMapper {

    List<City> getAllCity();

    City getCityById(@Param("id") Integer id);

    Integer insert(@Param("city") City city);

}
