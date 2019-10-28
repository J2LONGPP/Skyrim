package com.coder.mybatis.mapper;

import com.coder.mybatis.entity.User;

import java.util.List;

public interface IUserMapper {
    User selectByPrimaryKey(Integer id);
    List<User> selectAll();
}
