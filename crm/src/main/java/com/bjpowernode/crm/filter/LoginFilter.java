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
        System.out.println("过滤器执行了初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取请求路径
        String servletPath = request.getServletPath();

        //遍历所有不拦截的路径
        for(String url : urlList){
            if(url.equals(servletPath)){
                //放行
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
        System.out.println("过滤器执行销毁");
    }
}
