package com.xl.web.tag;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.Tag;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-05-20
 * @Time: 21:21
 * To change this template use File | Settings | File Templates.
 */
public class BodyTag extends XLBaseTag implements DynamicAttributes {
    private Map attributes = new HashMap();

    @Override
    public int doStartTag() throws JspException {
        StringBuffer sb = new StringBuffer("<body ");
        for (Object k : attributes.keySet()) {
            sb.append(k + "=\"" + attributes.get(k) + "\"");
        }
        sb.append(">");
        this.println(sb.toString());
        this.println("<div class=\"body-content\">");
        return Tag.EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        this.println("</div></body>");
        attributes.clear();
        return Tag.EVAL_BODY_INCLUDE;
    }

    @Override
    public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
        if (value != null) {
            attributes.put(localName, value);
        }
    }
}
