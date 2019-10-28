package com.demo.spring.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.util.Properties;

/**
 * @author long.yu
 * @date 2019-03-19
 * @desc 创建RedisConnectionFactory对象
 * 在spring中式通过RedisConnection接口操作Redis的,而RedisConnection则对原生的Jedis进行封装，要获取RedisConnection接口对象
 * 是通过RedisConnectionFactory接口去生成的
 */
/*@Configuration*/
public class RedisConfig {
    private RedisConnectionFactory connectionFactory=null;

    @Bean(name = "RedisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory(){
        if(this.connectionFactory!=null){
            return this.connectionFactory;
        }
      /*  JedisPoolConfig poolConfig=new JedisPoolConfig();
        poolConfig.setMaxIdle(8);
        poolConfig.setMaxTotal(50);
        poolConfig.setMaxWaitMillis(2000);*/
        //创建Jedis连接工厂
        /*JedisConnectionFactory connectionFactory=new JedisConnectionFactory(poolConfig);
        connectionFactory.setHostName("127.0.0.1");
        connectionFactory.setPort(6379);
        connectionFactory.setPassword("123456");
        this.connectionFactory=connectionFactory;*/
        //获取单机版的Redis配置
        return connectionFactory;
    }
}
