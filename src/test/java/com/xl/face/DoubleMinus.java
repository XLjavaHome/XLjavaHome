package com.xl.face;

import java.math.BigDecimal;

public class DoubleMinus {
    public static void main(String[] args) {
        System.out.println(new BigDecimal("2.00").subtract(new BigDecimal("1.1")));
        System.out.printf("%.1f", 2.0 - 1.1);
        System.out.println(2.0 - 1.1);
    }
}
