package com.xl.Tcp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class TextClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("192.168.1.254", 10006);
        BufferedReader bufr = new BufferedReader(new FileReader("ip.java"));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        //		long time=System.currentTimeMillis();
        //这个关联流，发送结束标记
        //		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        //		dos.writeLong(time);
        //		out.print(time);
        String line = null;
        while ((line = bufr.readLine()) != null) // 因为源是文件，所以可以结束
        {
            out.println(line);
        }
        //		out.println("over");
        //		dos.writeLong(time); //那边结束的标记
        s.shutdownOutput();//关闭客户端的输出流，相当于给流中加入了结束标记
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str = bufIn.readLine();
        System.out.println(str);
        bufr.close();
        s.close();
    }
}

