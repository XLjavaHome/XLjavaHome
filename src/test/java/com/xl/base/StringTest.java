package com.xl.base;

import com.xl.util.StringUtil;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringEscapeUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA. String类不可被继承
 * User: 徐立
 * Date: 2017/8/28
 * Time: 9:59
 * To change this template use File | Settings | File Templates.
 */
public class StringTest {
    String birthday = "111111111111111119900228";
    StringBuffer sql = new StringBuffer();

    @Before
    public void before() {
        sql.append("12e13,3232,aa");
    }

    /**
     * todo 转义
     */
    @Test
    public void transTest() {
        String s = "\\u79fb\\u52a8\\u4e92\\u8054\\u7f51\\u5e94\\u7528";
        String s2 = StringEscapeUtils.unescapeJava(s);
        System.out.println(s);
        System.out.println(s2);
    }

    /**
     * 是否匹配给定的正则
     */
    @Test
    public void matchTest() {
        System.out.println(birthday.matches("\\d{4,}"));
    }

    /**
     * 转换为join字符串
     */
    @Test
    public void joinTest() {
        String[] s = new String[]{"11", "", "22"};
        //有空格
        System.out.println(StringUtil.join(s, ","));
    }

    @Test
    public void spiltTest() {
        String s = "测试21321\n" + "'e234";
        System.out.println(Arrays.toString(s.split(",")));
    }

    @Test
    public void bufferAndBuilderTest() {
        StringBuilder sb = new StringBuilder();
        StringBuffer sb2 = new StringBuffer();
    }

    /**
     * 替换所有的字符串
     * replace 不使用正则
     * replaceAll可以正则
     */
    @Test
    public void replaceTest() {
        System.out.println("111.3333.444".replace(".", "测试"));
    }

    @Test
    public void deleteTest() {
        System.out.println(sql);
        sql.delete(sql.length() - 2, sql.length());
        System.out.println(sql);
    }

    @Test
    public void insertTest() {
        sql.insert(0, "测试插入");
    }

    @Test
    public void equalTest() {
        String s1 = "Hollis";
        String s2 = new String("Hollis");
        String s3 = new String("Hollis").intern();
        //false
        System.out.println(s1 == s2);
        //true
        System.out.println(s1 == s3);
    }

    /**
     * d数字，s字符串
     * MessageFormat 用%s占位是不行的
     */
    @Test
    public void placeHolderTest() {
        //二位小数,并且四舍五入
        String test = String.format("%.2f", 123.32621D);
        System.out.println(test);
        //按顺序占位，多一个%s会报错
        String s = String.format("测试%s占位符,%s", "hello world", "这是第二个");
        System.out.println(s);
        String str = String.format("格式参数$的使用：%1$d,%2$s,%2$s，%2$s", 99, "abc");
        System.out.println(str);
        //    占位符中的double类型
        double num = 123.4567899;
        System.out.print(String.format("%f %n", num)); // 123.456790
        System.out.print(String.format("%a %n", num)); // 0x1.edd3c0bb46929p6
        System.out.print(String.format("%g %n", num)); // 123.457
        double num2 = 3;
        System.out.print(String.format("%s", num2)); // 3.0
    }
    
    @Test
    public void MessageFormatTest() {
        //参数可以多没事
        String[] holder = {"hello", "测试"};
        String t2 = MessageFormat.format("MessageFormat占位符() {0},{1},", holder);
        System.out.println(t2);
    }
    
    @Test
    public void demoTest() {
        StringBuilder buf = new StringBuilder();
        StringBuilder workSheetSql = new StringBuilder();
        workSheetSql.append("-- 每个项目的每个工作单耗时\n" + "  left join (\n" + "              select t.projectid\n"
                            + "                , t2.ownerid\n" + "                , sum(t2.worktime) as worksheettime\n"
                            + "              -- 项目编号\n" + "              from sirmpm_worksheet t\n                inner join (\n"
                            + "--     每个员工每个工作单耗时多久\n" + "                             select tt1.worksheetid\n"
                            + "                               , tt1.ownerid\n"
                            + "                               , max(tt1.worktime) as worktime\n"
                            + "                             from (\n" + "-- 开发任务，美工任务\n"
                            + "                                    select tt2.worksheetid\n"
                            + "                                      , to_char(tt4.ownerid) as ownerid\n"
                            + "                                      , nvl(sum(tt4.worktime),0) as worktime\n"
                            + "                                    from sirmpm_worksheet tt1\n"
                            + "                                      left join sirmpm_requirement tt2 on tt2.worksheetid = tt1.objid\n"
                            + "                                      left join sirmpm_task tt3 on tt3.requirementid = tt2.objid\n"
                            + "                                      left join sirmpm_updatetasklog tt4 on tt4.taskid = tt3.objid\n"
                            + "                                    where tt4.ownerid is not null {0}\n"
                            + "                                                                   group by tt2.worksheetid,tt4.ownerid\n"
                            + "                                                                                                -- 测试任务\n"
                            + "                                                                                                union\n"
                            + "                                                                                                select tt2.worksheetid\n"
                            + "                                    ,to_char(tt4.ownerid)\n"
                            + "                                    ,nvl(sum(tt4.worktime),0) as testtasktime\n"
                            + "                                                              from sirmpm_worksheet tt1\n"
                            + "                                                              left join sirmpm_requirement tt2 on tt2.worksheetid = tt1.objid\n"
                            + "                                                                                                  left join sirmpm_testtask tt3 on tt3.requirementid = tt2.objid\n"
                            + "                                                                                                                            left join sirmpm_updatetesttasklog tt4 on tt4.taskid = tt3.objid\n"
                            + "                                                                                                                                                                                       where 1 = 1\n"
                            + "                                                                                                                                                                                       and tt4.ownerid is not null {1}\n"
                            + "                                                                                                                                                                                                          group by tt2.worksheetid,tt4.ownerid\n"
                            + "                                                                                                                                                                                                                                   union\n"
                            + "                                                                                                                                                                                                                                   -- 部署任务\n"
                            + "                                                                                                                                                                                                                                   select tt2.worksheetid\n"
                            + "                                    ,tt6.ownerid\n"
                            + "                                    ,nvl(sum(tt6.worktime),0)\n"
                            + "                                    from sirmpm_worksheet tt1\n"
                            + "                                    left join sirmpm_requirement tt2 on tt2.worksheetid = tt1.objid\n"
                            + "                                                                            left join sirmpm_task tt3 on tt3.requirementid = tt2.objid\n"
                            + "                                                                                                         left join sirmpm_taskdeploymentrela tt4 on tt4.taskid = tt3.objid\n"
                            + "                                                                                                                                             left join sirmpm_deploymenttask tt5 on tt4.deploymenttaskid = tt5.objid\n"
                            + "                                                                                                                                                                                                               left join sirmpm_deploymenttasklog tt6 on tt6.deploymenttaskid = tt5.objid\n"
                            + "                                                                                                                                                                                                                                                                                where tt6.ownerid is not null {2}\n"
                            + "                                                                                                                                                                                                                                                                                                              group by tt2.worksheetid,tt6.ownerid\n"
                            + "                                                                                                                                                                                                                                                                                                                                          -- 日常维护任务\n"
                            + "                                                                                                                                                                                                                                                                                                                                          union\n");
        Pattern p = Pattern.compile("append\\((.*)\\)\"\\)");
        Matcher matcher = p.matcher(workSheetSql);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }
}
