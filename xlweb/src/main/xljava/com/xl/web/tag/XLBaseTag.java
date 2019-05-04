package com.xl.web.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import lombok.extern.log4j.Log4j;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-20
 * @Time: 21:22
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public abstract class XLBaseTag extends BodyTagSupport {
    @Override
    public abstract int doStartTag() throws JspException;

    @Override
    public abstract int doEndTag() throws JspException;

    protected void println(String str) {
        try {
            this.pageContext.getOut().println(str);
        } catch (IOException e) {
            log.info(e);
        }
    }
}
