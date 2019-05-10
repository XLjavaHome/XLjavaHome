package com.xl.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 徐立
 * @Date: 2018-01-19
 * @Time: 13:49
 * To change this template use File | Settings | File Templates.
 */
public class JsonProcessor implements JsonValueProcessor {
    //下面都没用到?
    private String datePattern = "yyyhh";

    public JsonProcessor() {
        super();
    }

    public JsonProcessor(String format) {
        super();
        this.datePattern = format;
    }

    @Override
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    @Override
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    private Object process(Object value) {
        try {
            if (value instanceof Date) {
                SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
                String timeString = sdf.format((Date) value);
                if (timeString.indexOf("00:00:00") > -1) {
                    timeString = timeString.substring(0, 10);
                }
                return timeString;
            }
            return value == null ? "" : value.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String pDatePattern) {
        datePattern = pDatePattern;
    }
}
