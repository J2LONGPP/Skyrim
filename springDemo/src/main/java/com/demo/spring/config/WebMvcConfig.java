package com.demo.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author long.yu
 * @date 2018-10-08
 * @desc MVC配置
 * @version 0.0.1
 */
//@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

    /**
     * 重写添加拦截器方法并添加配置拦截器
     */
    public void addInterceptors(InterceptorRegistry registry){
        HandlerInterceptor handlerInterceptor=new HandlerInterceptor() {
            //处理器执行前
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                System.out.println("自定义拦截器.....");
                return true;
            }

            //处理器处理后方法
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

            }

            //处理器完成后方法
            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

            }
        };
        //注册拦截器，并指定匹配模式，限制拦截器拦截请求
        registry.addInterceptor(handlerInterceptor).addPathPatterns("/spring/**");
    }

}
