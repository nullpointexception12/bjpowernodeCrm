package com.bjpowernode.crm.service;

import com.bjpowernode.crm.common.ResultObj;

public interface UserService {
    ResultObj login(String username, String password,String ip);
}
