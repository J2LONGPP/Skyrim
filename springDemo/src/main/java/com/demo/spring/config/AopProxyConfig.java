package com.demo.spring.config;

import com.demo.spring.service.AopProxyServiceWithMethod;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 *  AOP 代理测试代码
 * @author long.yu
 * @date 2019-09-05
 * @version 0.0.1
 */
@Component("aop")
@Aspect
public class AopProxyConfig implements AopProxyServiceWithMethod {

    @Override
    public void query() {
        System.out.println("query......");
    }

    @Pointcut("execution(* com.demo.spring.config.*.*(..))")
    public void pointCut(){
        System.out.println("Pointcut ......");
    }

    @Before("com.demo.spring.config.AopProxyConfig.pointCut()")
    public void beforeMethod(){
        System.out.println("Before Method ......");
    }
}
