package com.demo.spring.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
/*import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;*/

import javax.sql.DataSource;
import java.net.Authenticator;

/***
 * 安全配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    Logger logger= LoggerFactory.getLogger(this.getClass());

    //使用数据库进行验证
    @Autowired
    private DataSource dataSource=null;

    //使用用户名称查询密码
    String pwdQuery="select tuser.user_name,tuser.pwd,tuser.available from t_user tuser where tuser.user_name=?";
    //使用用户名名称查询角色信息
    String roleQuery="select tu.user_name,tr.role_name from t_user tu,t_role tr,t_user_role tur " +
            "where tu.id=tur.id and tr.id=tur.id and tu.user_name=?";

    //注入配置的阴匙
    @Value("${system.user.password.secret}")
    private String secret=null;

    /**
     * 自定义配置
     * 用来配置拦截保护的请求 比如什么请求放行 什么请求需要验证
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
       /*httpSecurity.authorizeRequests()
               //都可以访问
               .antMatchers("/css/**","/js/**","/font/**","/index").permitAll()
               //user路径下的只有ADMIN用户角色才能访问
               .antMatchers("/user/**").hasRole("ADMIN")
               .and()
               .formLogin() //基于form表单验证
               .loginPage("/login").failureUrl("/login-error") //自定义登录页面
               .and()
               .logout()
               .logoutSuccessUrl("/index");*/

        /**
         * 配置请求路径访问权限
         * 使用 Ant风格配置限定
         */
        //限定签名后的权限
        /*httpSecurity.authorizeRequests()
                //限定 “user/welcome”请求赋予角色 ROLE_USER或者ROLE_ADMIN
        .antMatchers("/user/welcome","/user/details").hasAnyRole("USER","ADMIN")
                //限定 “admin/**”下所有请求权限赋予角色ADMIN
        .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                //其他路径允许签名后访问
        .anyRequest().permitAll()
                //对于没有配置权限的其他请求允许匿名访问
        .and().anonymous()
                //使用Spring Security默认的登录页面
        .and().formLogin()
                //启动HTTP基础验证
        .and().httpBasic();*/

        /**
         * Spring 表达式设置权限
         */
        /*httpSecurity.authorizeRequests()
                //使用Spring 表达式限定只有角色 ROLE_USER或者ROLE_ADMIN
        .antMatchers("/user/**").access("hasRole('USER') or hasRole('ADMIN')")
                //设置访问权限给角色ROLE_ADMIN,要求是完整登录（非记住我登录）
        .antMatchers("/admin/welcome1").access("hasAuthority('ROLE_ADMIN') && isFullyAuthenticated()")
                //限定“/admin/welcome2”访问权限给角色ROLE_ADMIN,允许不完整登录
        .antMatchers("/admin/welcome2").access("hasAuthority('ROLE_ADMIN')")
                //使用记住我的功能
        .and().rememberMe()
                //使用Spring Security默认的登录页面
        .and().formLogin()
                //启动HTTP基础验证
        .and().httpBasic();*/

        /**
         * 强制使用HTTPS协议采用证书对敏感的信息进行加密
         * requiresChannel 说明使用渠道
         * requiresSecure 表示使用HTTPS请求
         * requiresInsecure 取消安全请求的机制
         */
        /*httpSecurity
                //使用安全渠道，限定为https请求
        .requiresChannel().antMatchers("/admin/**").requiresSecure()
                //不使用Https请求
        .and().requiresChannel().antMatchers("/user/**").requiresInsecure()
                //限定允许访问的角色
        .and().authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ROLE","ADMIN");*/



        logger.debug("Using default configure(HttpSecurity) If subclassed this will potentially override sunclass configure(HttpSecurity)");
        /**
         * 只要通过验证就可以访问所有的请求
         * authorizeRequests 方法限定只对签名成功的用户请求
         * anyRequest 方法限定所有的请求
         * authenticated 方法对所有签名成功的用户允许方法
         */
        httpSecurity.authorizeRequests().anyRequest().authenticated()
                //formLogin代表用Spring Security默认的登录界面
                .and().formLogin()
                //httpBasic 方法说明启用Http基础验证
                .and().httpBasic();
    }

    /**
     * 认证信息管理
     * 用来配置用户签名服务，主要是user-details机制 还可以给予用户赋予角色
     * @param authenticationManagerBuilder  签名管理构造器 用于构建用户具体权限控制
     */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
       //使用内存签名服务-不加密
//      authenticationManagerBuilder.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");

        //使用加密的内存签名服务
        //密码编码器 SHA-256(SHA系列hash算法)+随机盐+密钥 结果不可逆
//        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        //使用内存存储
        /*authenticationManagerBuilder.inMemoryAuthentication()
                //设置密码编码器
        .passwordEncoder(passwordEncoder)
                //注册用户为admin 密码 abc 并赋予USER和ADMIN的角色权限
        .withUser("admin").password(passwordEncoder.encode("abc")).roles("USER","ADMIN")
                //连接方法and
        .and()
                //注册用户myuser 密码123456 并赋予USER角色权限
        .withUser("myuser").password(passwordEncoder.encode("123456")).roles("USER");*/

        //增加阴匙
        PasswordEncoder passwordEncoder1=new Pbkdf2PasswordEncoder(this.secret);
//        System.out.println(passwordEncoder1.encode("abc"));

        //使用数据库进行验证并赋予权限
        authenticationManagerBuilder.jdbcAuthentication().passwordEncoder(passwordEncoder1)
                //数据源
        .dataSource(dataSource)
                //查询用户，自行判断密码是否一致
        .usersByUsernameQuery(pwdQuery)
                //赋予权限
        .authoritiesByUsernameQuery(roleQuery);
    }

}
