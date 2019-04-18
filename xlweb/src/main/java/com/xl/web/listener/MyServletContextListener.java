package com.xl.web.listener;

import com.xl.util.Constant;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import lombok.extern.log4j.Log4j;

/**
 * Created with IntelliJ IDEA. 只在容器初始化的时候加载一次
 *
 * @author: 徐立
 * @Date: 2018-05-20
 * @Time: 10:56
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("ServletContext初始化" + Constant.INIT);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info(this.getClass().getName() + Constant.DESTROYED);
    }
}
