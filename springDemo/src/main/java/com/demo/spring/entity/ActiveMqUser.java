package com.demo.spring.entity;

import java.io.Serializable;

/**
 * 测试ActiveMQ 发送POJO 这个类要求这个对象能够进行序列化，也就是实现Serializable接口
 * @author long.yu
 * @date 2019-04-11
 * @version 0.0.1
 */
public class ActiveMqUser implements Serializable {
    //定义一个序列化版本号
//    private static final long serialVersionUID=8081849731640304905L;
    private Long id;
    private String userName=null;
    private String note=null;

    public ActiveMqUser(Long id,String userName,String note){
        this.id=id;
        this.userName=userName;
        this.note=note;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
