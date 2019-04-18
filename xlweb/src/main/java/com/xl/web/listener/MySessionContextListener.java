package com.xl.web.listener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import lombok.extern.log4j.Log4j;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-20
 * @Time: 11:37
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class MySessionContextListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        ServletContext app = event.getSession().getServletContext();
        int count = Integer.parseInt(app.getAttribute("onLineCount").toString());
        count++;
        log.info(count);
        app.setAttribute("onLineCount", count);
        int maxOnLineCount = Integer.parseInt(app.getAttribute("maxOnLineCount").toString());
        if (count > maxOnLineCount) {
            //记录最多人数是多少
            app.setAttribute("maxOnLineCount", count);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //记录在那个时刻达到上限
            app.setAttribute("date", df.format(new Date()));
        }
    }

    /**
     * session注销、超时时候调用，停止tomcat不会调用
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        ServletContext app = event.getSession().getServletContext();
        int count = Integer.parseInt(app.getAttribute("onLineCount").toString());
        count--;
        app.setAttribute("onLineCount", count);
    }
}
