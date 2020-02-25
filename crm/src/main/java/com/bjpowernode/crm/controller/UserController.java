package com.bjpowernode.crm.controller;

import com.bjpowernode.crm.bean.User;
import com.bjpowernode.crm.common.Constants;
import com.bjpowernode.crm.common.ResultObj;
import com.bjpowernode.crm.service.UserService;
import com.bjpowernode.crm.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

/**
 * create by br
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @RequestMapping("/index.do")
    public String index(HttpServletRequest request,HttpServletResponse response){
        
        //判断是否有cookie存在并检查用户名密码是否正确
        Cookie[] cookies = request.getCookies();
        if(cookies == null || cookies.length == 0){
            //不存在cookie跳转到登录页
            return "redirect:/login.jsp";
        }else {
            String value = null;
            for(Cookie cookie : cookies){
                String name = cookie.getName();
                if("userToken".equals(name)){
                    value = cookie.getValue();
                }
            }
            try {
                if(value!=null){
                    String json = URLDecoder.decode(value, "utf-8");
                    User user = JsonUtil.toObj(json, User.class);
                    ResultObj obj = userService.login(user.getLoginAct(), user.getLoginPwd(),request.getRemoteAddr());
                    if(Constants.code_200.equals(obj.code)){
                        return "redirect:/workbench/index.jsp";
                    }else {
                        return "redirect:/login.jsp?msg=" + obj.msg;
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/login.jsp";
    }

    @RequestMapping("/login.do")
    public  void login(@RequestParam(name = "username",required = false) String username,
                                      @RequestParam(name = "password",required = false) String password,
                                      @RequestParam(name = "sevenDayNoLogin",required = false) boolean sevenDayNoLogin,
                                      HttpServletRequest request, HttpServletResponse response){
        ResultObj resultObj = null;
        String ip = request.getRemoteAddr();
        try {
            resultObj = userService.login(username,password,ip);
            if(Constants.code_200.equals(resultObj.code)){
                if(sevenDayNoLogin){  //选择了7天免登录
                    Cookie cookie = new Cookie("userToken", URLEncoder.encode(JsonUtil.toJson(resultObj.data),"utf-8"));
                    System.out.println(URLEncoder.encode(JsonUtil.toJson(resultObj.data),"utf-8"));
                    cookie.setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(cookie);
                }

                request.getSession().setAttribute("userToken",resultObj.data);
                request.getSession().setMaxInactiveInterval(60 * 60);
            }
            response.getWriter().write(JsonUtil.toJson(resultObj));
        }catch (Exception e){
            System.out.println("登录异常," + e);
        }

    }

    @RequestMapping("/logout.do")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "/login";
    }

   /* @RequestMapping("/getAllUser.do")
    public String getAllUser(Model model){
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList", userList);
        return "/workbench/activity/index";

    }*/


}
