package com.xl.face;

public class MyClass {
    public static void main(String[] args) {
        System.out.println(MyClass.class.getName().
                replaceAll("\\.", "/") + ".class");
    }
}

