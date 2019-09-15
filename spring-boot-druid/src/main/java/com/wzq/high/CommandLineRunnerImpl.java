package com.wzq.high;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 实现CommandLineRunner的作用：
 *  实现在应用启动后，去执行相关代码逻辑，且只会执行一次
 * 注意： 当run方法中出现异常会导致整个程序停止
 * 处理： 另外起一个线程去处理run方法中的逻辑
 * @Order 指定runner的执行顺序， 越小，执行顺序越前
 *
 * 可以在run()方法里使用任何依赖，因为它们已经初始化好了；
 * runner执行是在spring容器初始化完成之后
 */
@Order(1)
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        new Thread(){
            public void run() {
                int i = 0;
                while (true) {
                    i++;
                    try {
                        Thread.sleep(10000);
                        System.out.println("过去了10秒钟……,i的值为：" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i == 4) { //第40秒时抛出一个异常
                        throw new RuntimeException();
                    }
                    continue;
                }
            }
        }.start();
    }
}
