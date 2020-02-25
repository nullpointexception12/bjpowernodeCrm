package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.bean.Activity;
import com.bjpowernode.crm.dao.ActivityMapper;
import com.bjpowernode.crm.dao.UserMapper;
import com.bjpowernode.crm.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:ActivityServiceImpl
 * Package:com.bjpowernode.crm.service.impl
 * Description:<br/>
 *
 * @date:2020/2/25 0025 15:11
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;


    @Override
    public int saveActivity(Activity activity) {
        //insert是必须插入全部数据
        //insertSelective可以只插入有数据的字段 没数据的忽略
        return activityMapper.insertSelective(activity);
    }


    @Override
    public List<Activity> getAllActivity(int pageNo, int pageSize, Map<String, Object> params) {
        if(params == null){
            params = new HashMap<>();
        }
        params.put("pageNo",pageNo);
        params.put("pageSize",pageSize);
        List<Activity> activityList = activityMapper.selectAllByParams(params);

        return activityList;
    }
}
