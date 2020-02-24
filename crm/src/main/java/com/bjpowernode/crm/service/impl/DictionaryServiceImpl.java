package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.bean.DicType;
import com.bjpowernode.crm.common.ResultObj;
import com.bjpowernode.crm.dao.DicTypeMapper;
import com.bjpowernode.crm.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DicTypeMapper dicTypeMapper;

    @Override
    public int saveDicType(DicType dicType) {
        return dicTypeMapper.insertSelective(dicType);
    }
}
