package com.xl.web.webInterface;

import com.xl.entity.PageLoadContext;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-24
 * @Time: 15:25
 * To change this template use File | Settings | File Templates.
 */
public interface PageLoadInterface {
    /**
     * 页面加载的执行方法
     *
     * @param context
     */
    void loadpage(PageLoadContext context);
}
