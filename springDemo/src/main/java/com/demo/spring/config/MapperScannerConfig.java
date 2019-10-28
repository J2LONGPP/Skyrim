package com.demo.spring.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

/**
 *  指定 xml位置
 */

/*@Configuration
@AutoConfigureAfter(MybatisConfig.class)*/ //保证在MybatisConfig实例化之后再实例化该类
public class MapperScannerConfig {

    //mapper接口的扫描器
    public MapperScannerConfigurer mapperScannerConfig(){
        MapperScannerConfigurer mapperScannerConfigurer=new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("classpath:mapper/**/*.xml");
        return mapperScannerConfigurer;
    }
}
