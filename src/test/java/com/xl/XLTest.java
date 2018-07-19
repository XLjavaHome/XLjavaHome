package com.xl;

import com.google.common.annotations.VisibleForTesting;
import com.xl.collections.CaseInsensitiveMap;
import com.xl.util.StringUtil;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import sun.text.resources.cldr.aa.FormatData_aa;

/**
 * Created with IntelliJ IDEA.
 * User: 徐立
 * Date: 2017/9/28
 * Time: 17:53
 * To change this template use File | Settings | File Templates.
 */
@Log4j
//可见测试
@VisibleForTesting
public class XLTest {
    @Test
    public void FormatData_aaTest() {
        String s = "";
        s.intern();
        FormatData_aa aa = new FormatData_aa();
        System.out.println(aa.getLocale());
        System.out.println(StringUtil.join(aa.getKeys()));
    }
    @Test
    public void mapTest() {
        Map map = new CaseInsensitiveMap();
        map.put("aaa", 11);
        map.put("aAA", 22);
        map.put("aAA", "测试");
        map.put("aA1A", "测试");
        map.put("aA1A", "测试2");
        log.info(map);
        System.out.println(map);
        System.out.println("这是输出");
    }

    @Test
    public void desktopTest() throws IOException {
        Desktop.getDesktop().browse(URI.create("http://127.0.0.1:8080"));
    }
}
