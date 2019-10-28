package com.coder.mybatis.excutor;

import com.coder.mybatis.config.MappedSatement;

import java.util.List;

/**
 * Mybatis核心接口之一，定义了数据库操作最基本的方法，SqlSession的功能就是基于它来实现的
 * @author long.yu
 */
public interface Executor {

    /**
     * 查询单条记录
     * @param ms 封装sql语句的MappedStatement对象
     * @param parameter 传入sql的参数
     * @param <E> 泛型
     * @return
     */
    <E> E selectOne(MappedSatement ms,Object parameter);
}
