package com.demo.spring.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 新版本我们可以采用两种方式来配置WebMvcConfigurer
 * 1. JavaBean方式配置WebMvcConfigurer
 * 2. WebMvcConfigurer实现类方式
 */
@Configuration
public class WebJavaBeanConfiguration {
    /**
     * 日志拦截器
     */

    /**
     * 实例化WebMsvcConfigurer接口
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {

            }

            @Override
            public void addFormatters(FormatterRegistry registry) {

            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {

            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("swagger-ui.html")
                        .addResourceLocations("classpath:/META-INF/resources/");
                registry.addResourceHandler("/**/*.html")
                        .addResourceLocations("classpath:static/");
            }

            //跨域CORS配置
            @Override
            public void addCorsMappings(CorsRegistry registry) {
               registry.addMapping("/myspring/**").allowedHeaders("*").allowedMethods("*").allowedOrigins("*");
            }

            //添加映射关系
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {

            }

            @Override
            public void configureViewResolvers(ViewResolverRegistry registry) {

            }
        };
    }
}
