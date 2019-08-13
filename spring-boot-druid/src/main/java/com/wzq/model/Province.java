package com.wzq.model;

import lombok.Data;

import java.util.List;

@Data
public class Province<T> {

    private List<T> data;
    private Integer id;
    private String provinceCode;
    private String provinceName;

}
