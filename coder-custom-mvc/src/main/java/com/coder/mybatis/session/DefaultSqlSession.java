package com.coder.mybatis.session;

import com.coder.mybatis.binding.MapperProxy;
import com.coder.mybatis.config.Configuration;
import com.coder.mybatis.excutor.DefaultExecutor;
import com.coder.mybatis.excutor.Executor;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * 1.对外提供服务，把请求转发给executor
 * 2.给mapper接口生成实现类
 * （配置文件解读+动态代理的增强）
 *   找到session中对应的方法执行
 *   找到命名空间和方法命
 *   传递参数
 */
public class DefaultSqlSession implements SqlSession{

    private Configuration configuration;

    //执行器
    private Executor executor;
    public DefaultSqlSession(Configuration configuration){
        this.configuration = configuration;
        this.executor = new DefaultExecutor(configuration);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        executor.selectOne(configuration.getMappedSatements().get(statement),parameter);
        return null;
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        return null;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        MapperProxy mapperProxy = new MapperProxy(this);
        return (T) Proxy.newProxyInstance(type.getClassLoader(),new Class[]{type},mapperProxy);
    }
}
