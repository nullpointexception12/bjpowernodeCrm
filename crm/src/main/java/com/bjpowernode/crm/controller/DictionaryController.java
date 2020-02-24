package com.bjpowernode.crm.controller;

import com.bjpowernode.crm.bean.DicType;
import com.bjpowernode.crm.common.Constants;
import com.bjpowernode.crm.common.ResultObj;
import com.bjpowernode.crm.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping("/saveDicType")
    public String saveDicType(@RequestParam(name = "code",required = false)String code,
                              @RequestParam(name = "name",required = false)String name,
                              @RequestParam(name = "describe",required = false)String describe,
                              Model model){
        ResultObj obj = new ResultObj();
        try {
            DicType dicType = new DicType();
            dicType.setName(name);
            dicType.setCode(code);
            dicType.setDescription(describe);
            int rows = dictionaryService.saveDicType(dicType);
            obj.code = rows == 1? Constants.code_200 : Constants.code_500;
            obj.msg = rows == 1? "插入成功":"插入失败";
            model.addAttribute("result",obj);
        }catch (Exception e){
           System.out.println("添加数据字典类型异常，" + e);
        }

        return "/settings/dictionary/type/index";
    }

}
