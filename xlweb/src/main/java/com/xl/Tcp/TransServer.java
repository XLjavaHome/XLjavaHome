package com.xl.Tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TransServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(10005);
        Socket s = ss.accept();
        String ip = s.getInetAddress().getHostAddress();
        System.out.println("客户端Ip" + ip);// 可以检验连没连上
        // OutputStream os=s.getOutputStream();
        // InputStream is =s.getInputStream();
        // 要一行行的读才方便
        // 读取socket读取流中的数据
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
        // 目的。socket输出流，将大写数据写入到socket输出流，并发送给客户端
        //		BufferedWriter bufOut = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        //		简化上面的代码
        PrintWriter out = new PrintWriter(s.getOutputStream(), true); //能接受字符流还能接受字节流还能自动刷新，true表示有效的刷新
        String line = null;
        while ((line = bufIn.readLine()) != null) // readLine读到回车符才算结束，所以客户端那边写入的时候只能写入回车符之前的数据
        {
            System.out.println(line);
            //			bufOut.write(line.toUpperCase()); // 写缓冲区里去了
            //			bufOut.newLine();
            //			bufOut.flush(); // 所以要刷新！
            out.println(line);  //带这换行的自动刷新
        }
        s.close();
        ss.close();
    }
}
