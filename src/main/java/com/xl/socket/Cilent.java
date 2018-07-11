package com.xl.socket;

import com.xl.entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cilent {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1", 9900);
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        Scanner sr = new Scanner(System.in);
        System.out.print("请输入人的姓名：");
        String name = sr.next();
        System.out.print("请输入人的年龄：");
        int age = sr.nextInt();
        User p = new User();
        p.setName(name);
        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
        oos.writeObject(p);
        System.out.println("传输成功");
        pw.flush();
        pw.close();
        s.close();
    }
}
