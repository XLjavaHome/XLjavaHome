package com.xl.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 徐立
 * @Date: 2018-05-27
 * @Time: 10:02
 * To change this template use File | Settings | File Templates.
 */
public class JSPUtil {
    /**
     * http://localhost:8080/
     *
     * @param request
     * @return
     */
    public static String getPath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    }
}
