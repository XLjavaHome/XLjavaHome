package com.xl.freemarker;

import com.xl.entity.Freemark;
import com.xl.entity.User;
import com.xl.util.FileUtil;
import com.xl.util.FreemarkerUtil;
import com.xl.util.ResourceUtil;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @User: li.xu
 * @Date: 2017/8/2
 * @Time: 17:12
 * To change this template use File | Settings | File Templates.
 */
public class FreemarkerTest {
    @Test
    public void demoTest() throws IOException, TemplateException {
        Freemark freemark = new Freemark("/freemarker/");
        freemark.setTempletName("简历-朱老师.ftl");
        freemark.setOutFile(ResourceUtil.createResourceFile("2.doc"));
        Map map = new HashMap<String, Object>();
        map.put("NAME", "徐立");
        map.put("image", FreemarkerUtil.getImage(ResourceUtil.getResourceFile("freemarker/a.jpg")));
        map.put("SEX", "男");
        map.put("BIRTH", "1987-08");
        map.put("ZZMM", "党员");
        map.put("MZ", "汉");
        map.put("JG", "河北省");
        map.put("JKZK", "良好");
        map.put("SG", "173cm");
        map.put("HYZK", "已婚");
        map.put("XL", "本科");
        map.put("BYYX", "河北工业大学");
        map.put("ZY", "软件工程");
        map.put("ZP", "照片//todo");
        map.put("QZYX", "软件方向工作薪资1w上下");
        map.put("JYSH1", "06-09-01~10-06-20");
        map.put("JYYZ1", "河北工业大学 软件工程");
        map.put("JYXW1", "学士学位");
        map.put("JYKC1", "软件工程、微积分、软件过程管理、数据库原理等等");
        map.put("JYSH2", "10-07-01~至今");
        map.put("JYYZ2", "ABCDE大学");
        map.put("JYXW2", "XYZ学位");
        map.put("JYKC2", "POI课程");
        map.put("ZYJN", "软件开发、软件管理");
        map.put("GZSH1", "10-07-01~11-12-09");
        map.put("GZDZ1", "华信软件");
        map.put("GZGY1", "初级软件工程师");
        map.put("GZSH2", "11-12-15~14-4-05");
        map.put("GZDZ2", "北京久其");
        map.put("GZGY2", "高级软件工程师");
        map.put("JLQK", "二三等奖学金、富士通奖学金等");
        map.put("ZWPJ", "兴趣丰富、好奇心强、有研究精神。");
        map.put("DH", "0312-3132098");
        map.put("SJ", "15033768387");
        map.put("YJ", "hanmanyifengyi@163.com");
        map.put("DZ", "河北省保定市");
        map.put("YB", "071000");
        freemark.setParam(map);
        FreemarkerUtil.createWord(freemark);
        FileUtil.open(freemark.getOutFile());
    }

    @Test
    public void listTest() throws IOException, TemplateException {
        Freemark freemark = new Freemark("/freemarker/");
        freemark.setTempletName("list.ftl");
        freemark.setOutFile(ResourceUtil.createResourceFile("1.doc"));
        Map map = new HashMap();
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 3; i++) {
            User u = new User();
            u.setName("测试" + i);
            list.add(u);
        }
        map.put("userlist", list);
        freemark.setParam(map);
        FreemarkerUtil.createWord(freemark);
        FileUtil.open(freemark.getOutFile());
    }

    @Test
    public void list2Test() throws IOException, TemplateException {
        Map map = new HashMap();
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 100; i++) {
            User u = new User();
            u.setName("测试" + i);
            list.add(u);
        }
        map.put("userlist", list);
        File f = ResourceUtil.createResourceFile("1.doc");
        FreemarkerUtil.analysisTemplate("freemarker/list.ftl", f.getPath(), map);
        FileUtil.open(f);
    }
}
