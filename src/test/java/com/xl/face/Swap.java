package com.xl.face;

import org.junit.Test;

public class Swap {
    int y = 111;
    private int x = 2001;

    public static void main(String[] args) {
        int x = 1984;
        int y = 2001;
        y = (x ^= (y ^= x)) ^ y;
        System.out.println("x= " + x + "; y= " + y);
    }

    @Test
    public void test() {
        System.out.println("1");
        y = x ^ y;
        System.out.println(y);
    }
}
