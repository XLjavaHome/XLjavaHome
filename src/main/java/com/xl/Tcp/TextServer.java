package com.xl.Tcp;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class TextServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10006);
        Socket s = ss.accept();
        String ip = s.getInetAddress().getHostAddress();
        System.out.println(ip);
        //先读一个long类型的结束标记
        //		DataInputStream dis =new DataInputStream(s.getInputStream());
        //		long l=dis.readLong();//先把时间戳读过来
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
        // 真是的应该把名字取一样。如果有同名判断一下
        PrintWriter out = new PrintWriter(new FileWriter("C:\\Documents and Settings\\All Users\\桌面\\server.txt"), true);
        String line = null;
        while ((line = bufIn.readLine()) != null) {
            out.println(line);
        }
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        pw.println("上传成功！");
        out.close();
        s.close();
        ss.close();
    }
}
