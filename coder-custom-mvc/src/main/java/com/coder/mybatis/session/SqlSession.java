package com.coder.mybatis.session;

import java.util.List;

/**
 * 封装ibatis接口
 * @author long.yu
 * @date 2019-08-23
 */
public interface SqlSession extends Cloneable {
    /**
     * 根据传入的条件查询单一结果
     * @param statement  方法对应的sql语句 namespace+id
     * @param parameter  要传入到sql语句中的查询参数
     * @param <T>
     * @return  返回指定的结果
     */
    <T> T selectOne(String statement,Object parameter);

    /**
     * 根据条件，返回泛型集合
     * @param statement  方法对应的sql语句 namespace+id
     * @param parameter  要传入到sql语句中的查询参数
     * @param <E>
     * @return  返回指定的结果对象的list
     */
    <E> List<E> selectList(String statement,Object parameter);

    <T> T getMapper(Class<T> type);
}
