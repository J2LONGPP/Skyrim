package com.demo.spring.util;


import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @desc mybatis通用 pagerHelper分页插件
 */
//@Configuration
public class PageHelperConfig {

    @Bean
    public PageHelper pageHelperConfig(){
        PageHelper pageHelper=new PageHelper();
        //params：在支持startPage(Object params)方法时，添加参数以根据属性名称配置参数映射为对象的值，可以配置pageNum,pageSize,count,pageSizeZero,reasonable，默认值为pageNum=pageNum;pageSize=pageSize;count=countSql;reasonable=reasonable;pageSizeZero=pageSizeZero。
        Properties properties=new Properties();
        //此参数RowBounds作为分页参数有效。当此参数设置为时true， offset参数in RowBounds用作 pageNum。
        properties.setProperty("offsetAsPageNum","true");
        //默认值为false，当此参数设置true为时，PageHelper将执行计数查询。
        properties.setProperty("rowBoundsWithCount","true");
        //分页参数合理化，默认值为false。当此参数设置为时true， pageNum <= 0将查询第一页， PageNum> pages（超过总数），将查询最后一页。默认情况下false，查询直接基于参数。
        properties.setProperty("reasonable","true");
        //使用PageHelper方式的默认分页，如果要实现自己的页面逻辑，可以实现Dialect（com.github.pagehelper.Dialect）接口，然后将属性配置为实现类的完全限定名称。
        properties.setProperty("dialect","oracle");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
