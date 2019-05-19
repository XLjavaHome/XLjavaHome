package com.xl.web;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-05-18
 * @time 23:27
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class DispacherbServet extends HttpServlet {
    /**
     * 会调用下面的init
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    @Override
    public void init() throws ServletException {
        super.init();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
