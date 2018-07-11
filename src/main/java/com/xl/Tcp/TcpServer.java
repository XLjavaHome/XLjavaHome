package com.xl.Tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        // 建立服务端Socket服务，并监听一个端口
        ServerSocket ss = new ServerSocket(10003);
        // 通过accept方法连接过来的客户端对象。
        Socket s = ss.accept();
        String ip = s.getInetAddress().getHostAddress();
        System.out.println("ip地址是：" + ip);
        // 获取客户端发送过来的数据，那么要用客户端的对象读取流读取对象
        InputStream in = s.getInputStream();
        byte[] buf = new byte[1024];
        int len = in.read(buf);
        System.out.println(new String(buf, 0, len));
        // udp通过数据包获取对方IP
        s.close(); // 关对方,
        ss.close();// 可选操作
    }
}
