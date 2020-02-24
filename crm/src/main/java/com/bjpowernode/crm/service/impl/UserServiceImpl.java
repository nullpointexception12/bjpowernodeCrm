package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.bean.User;
import com.bjpowernode.crm.common.Constants;
import com.bjpowernode.crm.common.ResultObj;
import com.bjpowernode.crm.dao.UserMapper;
import com.bjpowernode.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultObj login(String username, String password,String ip) {
        ResultObj resultObj = new ResultObj();
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        User user = userMapper.selectByActAndPwd(username,password);
        if(user == null){
            resultObj.code = Constants.code_500;
            resultObj.msg = "用户名密码错误";
        }else if(!(user.getExpireTime() == null || user.getExpireTime().compareTo(now) >= 1 )){
            resultObj.code = Constants.code_500;
            resultObj.msg = "当前账户已失效";
        }else if(!(user.getLockState() == null || "1".equals(user.getLockState())  )){
            resultObj.code = Constants.code_500;
            resultObj.msg = "当前账户已锁定";
        }else if(!(user.getAllowIps() == null || !user.getAllowIps().contains(ip))){
            resultObj.code = Constants.code_500;
            resultObj.msg = "您的ip已被限制访问";
        }else {
            resultObj.code = Constants.code_200;
            resultObj.data = user;
        }

        return resultObj;
    }
}
