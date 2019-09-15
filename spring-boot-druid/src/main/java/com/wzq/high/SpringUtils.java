package com.wzq.high;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 作用：
 *      1. 可通过工具类直接获取容器中的bean
 *      2. 可在非容器管理的bean中使用容器中的bean
 */
@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringUtils.context == null){
            SpringUtils.context = applicationContext;
        }
    }

    public static ApplicationContext getContext(){
        return context;
    }

    public static Object getBean(String name){
        return getContext().getBean(name);
    }
    public static <T> T getBean(Class<T> clazz){
        return getContext().getBean(clazz);
    }
    public static <T> T getBean(String name,Class<T> clazz){
        return getContext().getBean(name, clazz);
    }
}
