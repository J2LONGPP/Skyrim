package com.demo.spring;
import com.demo.spring.service.AopProxyServiceWithMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//取消数据库自动配置
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@SpringBootApplication
//扫描dao层包
//@MapperScan("com.demo.spring.mapper")
//扫描使用注解的service
@ServletComponentScan
//扫描事务
@EnableTransactionManagement
@EnableSwagger2
@EnableAspectJAutoProxy
//@EnableScheduling
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
