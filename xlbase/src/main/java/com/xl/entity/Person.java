package com.xl.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person implements Serializable {
    // 唯一序列化标识
    public static final long serialVersionUID = 42L; // 值自己取名
    public String name = "这是name";
    /**
     * transient 该字段无法序列化，所以传输对象的时候不会传过去
     */
    transient String pas;
    private int age = 13;
    // private int password=123;
    private String password;
    private Date birthday;
    private Student student;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
}
