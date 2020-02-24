package com.bjpowernode.crm.dao;

import com.bjpowernode.crm.bean.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByActAndPwd(String username, String password);
}