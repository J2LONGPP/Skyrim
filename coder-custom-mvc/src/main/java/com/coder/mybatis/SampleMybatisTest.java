package com.coder.mybatis;

import com.coder.mybatis.entity.User;
import com.coder.mybatis.mapper.IUserMapper;
import com.coder.mybatis.session.SqlSession;
import com.coder.mybatis.session.SqlSessionFactory;

/**
 * Mybatis核心流程三大阶段
 * 1.读取XML配置文件和注解中的配置信息，创建配置对象，并完成各个模块的初始化工作
 * 2.封装iBtis的编程模型，使用mapper接口开发的初始化工作
 * 3.通过SqlSession完成SQL的解析、参数的映射、SQL的执行、结果的反射解析过程
 */
public class SampleMybatisTest {
    public static void main(String[] args) {
        //实例化SqlSessionFactory,加载数据库配置文件以及mapper.xml文件到configuration对象中
        SqlSessionFactory factory = new SqlSessionFactory();
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //通过动态代理跨越面向接口变成和ibatis编程模型的鸿沟
        IUserMapper userMapper = sqlSession.getMapper(IUserMapper.class);
        //遵循jdbc规范，通过底层的四大对象的合作完成数据查询和数据转化
        User user = userMapper.selectByPrimaryKey(1);

    }
}
