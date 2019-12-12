package com.xl.entity;

import com.xl.excel.annotation.Cell;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student implements Serializable {
    @Cell(title = "学号")
    private transient int id;
    private String sex;
    @Cell(title = "姓名")
    private transient String name;
    @Cell(title = "地址")
    private String address;
    private transient int age;
    private String phone;
    private Teacher teacher;
    private int objid;
    
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
    
    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.defaultWriteObject();
        outputStream.writeInt(id);
        outputStream.writeInt(age);
    }
    
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.id = s.readInt();
        this.age = s.readInt();
    }
}
