package com.wzq.constant;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PropertiesConstant {

    @NacosValue(value = "${nacos.test.properties:123}", autoRefreshed = true)
    private String test;

}
