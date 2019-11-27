package com.xl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xl.entity.Student;
import java.util.Date;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-08
 * @time 22:54
 * To change this template use File | Settings | File Templates.
 */
public class FastjsonTest {
    @Test
    void name() {
        com.xl.entity.Student obj = new Student();
        obj.setId(0);
        obj.setName("张三");
        obj.setAddress("武汉中山公园");
        obj.setAge(0);
        obj.setPhone("");
        //没有就为空
        String jsonString = JSON.toJSONString(obj);
        System.out.println(jsonString);
        //反序列化
        Student vo = JSON.parseObject(jsonString, Student.class);
        System.out.println(vo);
    }
    
    @Test
    void demo2() {
        Student stutent = new Student();
        stutent.setName("测试");
        stutent.setBirthday(new Date());
        String string = JSONObject.toJSONString(stutent);
        System.out.println(string);
        JSONObject jsonObject = JSONObject.parseObject(string);
        com.xl.entity.Student student = JSONObject.toJavaObject(jsonObject, Student.class);
        System.out.println(student);
    }
}
