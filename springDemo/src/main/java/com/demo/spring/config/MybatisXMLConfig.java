package com.demo.spring.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 *  mybatis 自定义配置  外部 xml导入
 */
/*@Configuration*/
/*@PropertySource(value = "classpath:jdbc-properties")*/  //导入外部配置文件
public class MybatisXMLConfig {

    /*@Autowired
    private DataSource dataSource;*/

    @Bean
    @ConditionalOnMissingBean //当容器中没有指定的Bean时创建
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        //设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        //设置mybatis的主配置文件
        ResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
        Resource mybatisConfigXml=resolver.getResource("classpath:mybatis/mybatis-config.xml");
        //设置别名包
        sqlSessionFactoryBean.setTypeAliasesPackage("com.demo.spring.entity");

        return sqlSessionFactoryBean;
    }
}
