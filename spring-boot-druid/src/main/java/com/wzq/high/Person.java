package com.wzq.high;

import lombok.Data;
import org.springframework.beans.factory.BeanNameAware;

@Data
public class Person implements BeanNameAware {

    private String beanName;
    private String name;
    private Integer age;
    private String address;

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

}
