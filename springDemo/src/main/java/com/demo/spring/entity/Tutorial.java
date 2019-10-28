package com.demo.spring.entity;

import java.io.Serializable;

/**
 * es测试类
 * 教程
 */
public class Tutorial implements Serializable {
    private Long id;//教程
    private String name;//教程名称

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
