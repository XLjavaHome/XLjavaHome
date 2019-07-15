package com.example.demo.web;

import java.io.IOException;
import javax.servlet.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-13
 * @time 16:58
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class XLFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
        filterChain.doFilter(servletRequest, servletResponse);
    }
    
    @Override
    public void destroy() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
