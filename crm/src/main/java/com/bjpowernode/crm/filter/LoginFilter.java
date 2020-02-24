package com.bjpowernode.crm.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class LoginFilter implements Filter {

    private static List<String> urlList = new ArrayList<>();

    static{
        urlList.add("/login.do");
        urlList.add("/login.jsp");
        urlList.add("/index.jsp");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String servletPath = request.getServletPath();

        for(String url : urlList){
            if(url.equals(servletPath)){
                filterChain.doFilter(request,response);
                return;
            }
        }
        //判断是否登录过
        Object userToken = request.getSession().getAttribute("userToken");
        //获取cookie信息
        Cookie[] cookies = request.getCookies();
        if (cookies == null && userToken == null){
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }else {
            filterChain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
