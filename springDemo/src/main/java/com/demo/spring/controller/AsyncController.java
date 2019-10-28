package com.demo.spring.controller;

import com.demo.spring.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异步控制器
 * @author long.yu
 * @date 2019-04-09
 * @version 0.0.1
 */
@RestController
@RequestMapping("/async")
public class AsyncController {
    //注入异步服务接口
    @Autowired
    private AsyncService asyncService=null;

    @GetMapping("/page")
    public String asyncPage(){
        System.out.println("请求的线程名称："+Thread.currentThread().getName());
        //调用异步服务
        asyncService.generateReport();
        return "async";
    }

}
