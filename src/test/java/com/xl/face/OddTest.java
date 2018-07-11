package com.xl.face;

public class OddTest {
    public static void main(String[] args) {
        for (int i = -10; i <= 10; i++) {
            System.out.println(isOdd(i));
        }
    }

    public static boolean isOdd(int i) {
        return i % 2 != 0;
    }
}
