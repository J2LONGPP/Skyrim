package com.demo.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 使用JAVA配置定义线程池和启用异步
 * @author long.yu
 * @date 2019-04-09
 * @version 0.0.1
 * @mark 除了Redis发布订阅的应用外都是同步应用，也就是一个请求都是在同一个线程中运行。但是有时候需要异步，也就是一个
 * 请求可能存在两个或者以上的线程。在实际的场景中，如后台管理系统，有些任务需要操作比较多的数据进行统计分析，典型的如报表，需要去生成，而报表
 * 可能需要访问的是亿级数据量并且进行比较复杂的运算。
 * Spring 中存在一个AsyncConfigurer接口，它是一个可以配置异步线程池的接口
 * @EnableAsync注解代表开启Spring异步
 * 当方法被标注@Async时，Spring就会通过这个线程池的空闲线程去运行该方法。
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    //定义线程池
    public Executor getAsyncExecutor(){
        //定义线程池
        ThreadPoolTaskExecutor threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
        //核心线程数
        threadPoolTaskExecutor.setCorePoolSize(10);
        //线程池最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(30);
        //线程队列最大线程数
        threadPoolTaskExecutor.setQueueCapacity(2000);
        //初始化
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
