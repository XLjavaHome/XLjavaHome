package com.xl.entity;

import com.xl.excel.annotation.Cell;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    @Cell(title = "学号")
    private int id;
    private String sex;
    @Cell(title = "姓名")
    private String name;
    @Cell(title = "地址")
    private String address;
    private int age;
    private String phone;

    public Student() {
    }

    public Student(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
