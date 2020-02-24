package com.bjpowernode.crm.util;

import com.alibaba.fastjson.JSONObject;


public class JsonUtil {

    public static String toJson(Object obj){
       return JSONObject.toJSONString(obj);
    }

    public static <T> T toObj(String json,Class<T> clz){
        return JSONObject.parseObject(json,clz);
    }

}
