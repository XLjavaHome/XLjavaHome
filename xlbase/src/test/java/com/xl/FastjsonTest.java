package com.xl;

import com.alibaba.fastjson.JSON;
import com.xl.entity.Student;
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
        Student obj = new Student();
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
}
