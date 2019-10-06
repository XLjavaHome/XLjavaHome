package com.xl.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import lombok.Data;

@Data
public class User implements Serializable {
    /**
     * volatile的适用场景 如果一个线程读和写一个volatile变量，其它线程只读取这个变量，然后读线程可以确保看到这个变量最后的被修改的值。如果这个变量不被修改为volatile，将没有这样的担保
     * 发生在主存上的读写操作的代价要高于访问CPU缓存
     * volatile阻止了指令重排序这种性能优化技术
     */
    private volatile int id;
    private String name;
    private String userName;
    private transient String passWord;
    private int age;
    private String sal;
    private String psw;
    private int credits;
    private Date lastVisit;
    private String lastIp;
    private Book book;
    private Address address;
    
    public User() {
    }
    
    public User(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
    }
    
    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }
}
