package com.xl;

import com.xl.entity.Student;
import com.xl.entity.Teacher;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-11-10
 * @time 17:54
 * To change this template use File | Settings | File Templates.
 */
public class circularDependencies {
    /**
     * toString会造成循环依赖
     */
    @Test
    void name() {
        Teacher teacher = new Teacher();
        teacher.setName("李雷");
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student s = new Student();
            s.setName("测试" + i);
            s.setTeacher(teacher);
            students.add(s);
        }
        teacher.setStudents(students);
        List<Student> studentList = teacher.getStudents();
        studentList.forEach(s -> System.out.println(s.getName()));
        System.out.println(teacher);
    }
}
