package com.bjpowernode.crm.init;


import com.bjpowernode.crm.common.Constants;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 服务启动时加载数据字典
 */
public class ServerInit implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        //获取spring上下文容器
        WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        Constants.context = springContext;

        System.out.println("你好");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
