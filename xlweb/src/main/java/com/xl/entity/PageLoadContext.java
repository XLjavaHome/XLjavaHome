package com.xl.entity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 徐立
 * @Date: 2017-12-29
 * @Time: 16:33
 * To change this template use File | Settings | File Templates.
 */
@Data
public class PageLoadContext {
    private PageContext pageContext;
    private HttpServletRequest request;
    private HttpServletResponse response;
    /**
     * todo 第几次加载
     */
    private boolean postback = false;
}
