package com.coder.mybatis.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据库配置信息
 * @author long.yu
 * @date 2019-08-23
 */
public class Configuration {
    //数据库驱动
    private String jdbcDriver;
    //数据库连接地址
    private String jdbcUrl;
    //数据库连接用户名
    private String userName;
    //数据库连接密码
    private String userPassword;

    //待执行的数据库SQL语句
    private Map<String,MappedSatement> mappedSatements = new HashMap<>();

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public Map<String, MappedSatement> getMappedSatements() {
        return mappedSatements;
    }

    public void setMappedSatements(Map<String, MappedSatement> mappedSatements) {
        this.mappedSatements = mappedSatements;
    }
}
