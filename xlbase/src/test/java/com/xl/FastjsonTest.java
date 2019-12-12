package com.xl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xl.entity.Student;
import com.xl.entity.Teacher;
import com.xl.util.FileUtil;
import com.xl.util.IOUtil;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
    /**
     * 多个json转换为string
     */
    @Test
    void listJsonToString() {
        Student student = initStudent();
        List list = new ArrayList();
        list.add(student);
        Teacher teacher = initTeacher();
        list.add(teacher);
        //多个对象放list集合中
        //id和age都在
        Object o = JSON.toJSON(list);
        System.out.println(o.toString());
        //会忽略掉age。。
        String last = JSON.toJSONString(list);
        System.out.println(last);
        //字符串转jsonArray对象
        ArrayList arrayList = JSON.parseObject(o.toString(), ArrayList.class);
        System.out.println(arrayList);
        ArrayList arrayList2 = JSON.parseObject(last, ArrayList.class);
        System.out.println(arrayList2);
    }
    
    private Student initStudent() {
        Student student = new Student();
        student.setId(0);
        student.setSex("1");
        student.setName("张三");
        student.setAddress("1");
        student.setAge(0);
        student.setTeacher(new Teacher());
        return student;
    }
    
    private Teacher initTeacher() {
        Teacher teacher = new Teacher();
        teacher.setName("李四");
        return teacher;
    }
    
    @Test
    void oneJsonToString() {
        Student student = initStudent();
        Teacher teacher = initTeacher();
        //需要强转
        JSONObject studentJsonObject = (JSONObject) JSON.toJSON(student);
        JSONObject teacherJsonObject = (JSONObject) JSON.toJSON(teacher);
        //内部做了非空判断
        String name = studentJsonObject.getString("name");
        System.out.println(name);
        String teacherStr = "teacher";
        studentJsonObject.put(teacherStr, teacher);
        System.out.println(studentJsonObject);
        //teacher是否是jsonObject？是
        //getJSONObject 是获取jsonObject，get可以直接获取该对象
        JSONObject jsonObject = studentJsonObject.getJSONObject(teacherStr);
        System.out.println(jsonObject);
        //重新赋值json字符串了还能转对象？ 可以
        //toString内部就是调用toJSONString
        Student student2 = JSON.parseObject(studentJsonObject.toJSONString(), Student.class);
        Teacher teacher2 = student2.getTeacher();
        System.out.println(teacher2);
    }
    
    /**
     * 是否忽略大小写？区分大小写
     */
    @Test
    void ignoreCase() {
        Teacher teacher = initTeacher();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("t", teacher);
        System.out.println(jsonObject);
        jsonObject.put("T", teacher);
        System.out.println(jsonObject);
        jsonObject.put("a", teacher);
        System.out.println(jsonObject);
    }
    
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
    
    @Test
    void demo2() {
        Student stutent = new Student();
        stutent.setName("测试");
        String string = JSONObject.toJSONString(stutent);
        System.out.println(string);
        JSONObject jsonObject = JSONObject.parseObject(string);
        Student student = JSONObject.toJavaObject(jsonObject, Student.class);
        System.out.println(student);
    }
    
    @Test
    void ioTest() throws IOException {
        JSONObject jsonObject = new JSONObject();
        File desktopFile = FileUtil.getDesktopFile("移动端-售前支持优化20191021.docx");
        InputStream fileInputStream = new FileInputStream(desktopFile);
        byte[] bytes = IOUtil.getBytes(fileInputStream);
        jsonObject.put("xl", bytes);
        String jsonString = jsonObject.toString();
        //将json字符串转为jsonOBject对象
        JSONObject parseObject = JSON.parseObject(jsonString);
        //将输入写入流
        byte[] xls = parseObject.getBytes("xl");
        InputStream input = new ByteArrayInputStream(xls);
        File temp = FileUtil.getTempFile(".doc");
        FileUtil.write(temp, input);
        FileUtil.openParent(temp);
    }
    
    @Test
    void objidTest() {
        Student student = new Student();
        student.setId(10);
        student.setObjid(20);
        student.setName("测试");
        String string = JSONObject.toJSONString(student);
        System.out.println(string);
    }
}
