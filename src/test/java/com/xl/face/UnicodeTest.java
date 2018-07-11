package com.xl.face;

public class UnicodeTest {
    public static void main(String[] args) {
        // \u0022 是双引号的 Unicode转义字符 
        System.out.println("a\u0022.length() +\u0022b".length());
        System.out.println("a".length() + "b".length());
    }
}
