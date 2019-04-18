package com.xl.base;

import java.util.Scanner;

public class PrintRhombusTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入要打印拼成菱形上半个三角形的行数,rows = ");
        int rows = input.nextInt();
        int i, j, k; // 初始化变量
        for (i = 1; i <= rows; i++) {
            // 外层循环控制行数控制台接收的参数rows即为上半个三角形的行数
            // 内循环一控制空格的输出
            for (j = 1; j <= rows - i; j++) {
                System.out.print(" ");
            }
            // 内循环二控制*的输出
            for (k = 1; k <= 2 * i - 1; k++) {
                // 判断是本行的第一个和最后一个打印*
                if (k == 1 || k == 2 * i - 1) {
                    System.out.print("*");
                }// 其他的都输出空格
                else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
        // 下半个三角形
        for (i = 1; i <= rows - 1; i++) {
            // 外层循环控制行数下半个三角形比上面的少一行所以rows-1
            for (j = 1; j <= i; j++) {
                System.out.print(" ");
            }
            for (k = 1; k < (rows - i) * 2; k++)
            // 第一个为1最后一个是(rows-i)*2-1,因为k<(row-i)*2不是<=
            {
                if (k == 1 || k == (rows - i) * 2 - 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }
}
