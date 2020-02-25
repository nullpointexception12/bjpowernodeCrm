package com.bjpowernode.crm.service;

import com.bjpowernode.crm.bean.User;
import com.bjpowernode.crm.common.ResultObj;

import java.util.List;

public interface UserService {
    ResultObj login(String username, String password,String ip);

    List<User> getAllUser();
}
