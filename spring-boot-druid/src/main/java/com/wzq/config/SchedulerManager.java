package com.wzq.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * 定时任务管理器
 *
 * @author wuzhiqiang
 * @Date 2019/5/5 10:21
 */
@Service
public class SchedulerManager {

    private Scheduler scheduler;

    @PostConstruct
    public void init() throws SchedulerException {
        Properties prop = new Properties();
        prop.put("org.quartz.scheduler.instanceName", "Scheduler");
        prop.put("org.quartz.scheduler.instanceId", "AUTO");
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount", "1000");
        prop.put("org.quartz.threadPool.threadPriority", "5");
        prop.put("org.quartz.threadPool.makeThreadsDaemons", "true");
        prop.put("org.quartz.scheduler.threadName", "collector-thread");
        prop.put("org.quartz.jobStore.misfireThreshold", "60000");

        SchedulerFactory sf = new StdSchedulerFactory(prop);
        scheduler = sf.getScheduler();
    }

    public Scheduler getScheduler(){
        return scheduler;
    }

}
