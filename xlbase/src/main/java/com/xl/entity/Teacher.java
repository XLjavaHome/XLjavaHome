package com.xl.entity;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-11-10
 * @time 17:08
 * To change this template use File | Settings | File Templates.
 */
@Setter
@Getter
public class Teacher {
    private String name;
    private List<Student> students;
    
    @Override
    public String toString() {
        return "Teacher{" + "name='" + name + '\'' + '}';
    }
}
