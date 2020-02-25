package com.bjpowernode.crm.util;

import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.crm.bean.DicType;
import com.bjpowernode.crm.bean.DicValue;
import com.bjpowernode.crm.bean.User;


public class JsonUtil {

    //使用fastJson来创建工具类
    public static String toJson(Object obj){
       return JSONObject.toJSONString(obj);
    }

    public static <T> T toObj(String json,Class<T> clz){
        return JSONObject.parseObject(json, clz);
    }


 }
