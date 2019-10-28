package com.coder.mybatis.excutor;

import com.coder.mybatis.config.Configuration;
import com.coder.mybatis.config.MappedSatement;
import com.coder.mybatis.entity.User;

import java.sql.*;

public class DefaultExecutor implements Executor {

    private Configuration configuration = new Configuration();

    public DefaultExecutor(Configuration configuration){
        this.configuration = configuration;
    }
    @Override
    public <E> E selectOne(MappedSatement ms, Object parameter) {
        System.out.println(ms.getSourceId());
        System.out.println(ms.getSql());
        executorQuery(ms,parameter);
        return null;
    }

    public Connection getConnection(Configuration configuration){
        Connection connection=null;
        //加载驱动
        try {
            if(connection==null){
                Class.forName(configuration.getJdbcDriver());
                connection = DriverManager.getConnection(configuration.getJdbcUrl(),configuration.getUserName(),configuration.getUserPassword());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public <T> T executorQuery(MappedSatement ms,Object parameter){
        Connection connection = getConnection(configuration);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //创建preparedStatement，并执行
        try {
            preparedStatement = connection.prepareStatement(ms.getSql());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("id"));
                System.out.println(resultSet.getString("name"));
            }
            //将结果集通过反射技术，填充到list中
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
