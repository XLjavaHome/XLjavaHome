package com.xl.web.tag;

import com.xl.entity.PageLoadContext;
import com.xl.web.webInterface.PageLoadInterface;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import lombok.Data;
import lombok.extern.log4j.Log4j;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2017-12-29
 * @Time: 16:07
 * To change this template use File | Settings | File Templates.
 */
@Data
@Log4j
public class LoadPageTag extends BodyTagSupport {
    private String clazz = null;

    @Override
    public int doStartTag() throws JspException {
        Class c = null;
        try {
            c = Class.forName(this.clazz.trim());
            PageLoadContext pc = getPageLoadContext();
            Class[] interfaces = c.getInterfaces();
            for (Class anInterface : interfaces) {
                //判断一个类是否属于一个类
                if (anInterface.isAssignableFrom(PageLoadInterface.class)) {
                    Method[] methods = anInterface.getMethods();
                    Method m = c.getMethod(methods[0].getName(), PageLoadContext.class);
                    // 加载页面
                    m.invoke(c.newInstance(), pc);
                }
            }
        } catch (Exception e) {
            log.info(e);
        }
        return EVAL_PAGE;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    private PageLoadContext getPageLoadContext() {
        PageLoadContext pc = new PageLoadContext();
        HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) this.pageContext.getResponse();
        pc.setPageContext(pageContext);
        pc.setRequest(request);
        pc.setResponse(response);
        return pc;
    }
}
